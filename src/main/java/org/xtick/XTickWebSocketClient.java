package org.xtick;


import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import jakarta.websocket.*;
import org.apache.commons.lang3.StringUtils;
import org.xtick.bean.CommonPacket;
import org.xtick.bean.TickSubcribeInfo;
import org.xtick.constant.XTickConst;
import org.xtick.util.JsonUtil;
import org.xtick.util.XTickUtil;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * WebSocket客户端，用于连接XTick的WebSocket服务。
 * 官网：http://www.xtick.top/
 * 订阅数据按照证券交易所订阅推送，包括上交所、深交所、北交所、港交所（只支持部分股票）。
 * 数据为实时推送，发数据非常快，客户端接受到数据后，最好做异步处理，将接受数据和数据处理分开，避免接受数据阻塞。切记...切记...切记：数据接受和数据处理，务必放在两个线程中，不要阻塞数据接受。
 * 1. 订阅方法：
 * 订阅数据：订阅为Websocket API，请在Github上下载开源项目，参考XTickWebSocketClient.java中已实现的订阅功能。
 * 入参1：authCodes 枚举取值如下：
 * 请不要订阅多余数据，多余数据会影响数据推送效率。比如只需要股票数据，就不需要订阅指数和场内基金的数据。
 * <p>
 * 按交易所订阅：
 * 深交所：tick.SZ.1  tick.SZ.10  tick.SZ.20
 * 上交所：tick.SH.1  tick.SH.10  tick.SH.20
 * 北交所：tick.BJ.1
 * 港交所：tick.HK.3
 * <p>
 * 订阅time数据可取枚举值如下：
 * 深交所：time.SZ.1  time.SZ.10  time.SZ.20
 * 上交所：time.SH.1  time.SH.10  time.SH.20
 * 北交所：time.BJ.1
 * 港交所：time.HK.3
 * <p>
 * 按股票个数订阅，最多50个股票：
 * - 000001.SZ - 订阅深交所平安银行000001的tick数据。支持按股票个数订阅，包括沪深京港四个交易所的股票，最多订阅50个。
 * - 000002.SZ - 订阅深交所万科000002的tick数据。支持按股票个数订阅，包括沪深京港四个交易所的股票，最多订阅50个。
 * 入参2：token 登录XTick网站，注册获取
 */
@ClientEndpoint
public class XTickWebSocketClient {
    private URI endpointURI;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private LinkedBlockingQueue<CommonPacket> queue = new LinkedBlockingQueue<>(100000);
    private ThreadPoolExecutor taskThreadPool = new ThreadPoolExecutor(3, 3, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1000), new ThreadFactoryBuilder().setNameFormat("xtick-task-%d").build());

    private Consumer<String> dataConsumer = result -> {
        CommonPacket packet;
        if (StringUtils.isNotBlank(result)) {
            packet = JsonUtil.jsonToObj(result, CommonPacket.class);
            if (Objects.nonNull(packet)) {//数据包加入队列中，后续业务模块调用处理
                queue.offer(packet);
            }
        }
    };

    private XTickWebSocketClient(URI endpointURI) {
        try {
            this.endpointURI = endpointURI;
            connectToServer(endpointURI, null);
        } catch (Exception e) {
            System.err.println("Failed to connect to WebSocket server。" + e.getMessage());
        }
    }

    private void connectToServer(URI endpointURI, CloseReason reason) {
        if (Objects.isNull(reason) || !reason.getCloseCode().equals(CloseReason.CloseCodes.NORMAL_CLOSURE)) {
            try {
                WebSocketContainer container = ContainerProvider.getWebSocketContainer();
                container.setDefaultMaxBinaryMessageBufferSize(1 * 1024 * 1024);
                container.setDefaultMaxTextMessageBufferSize(1 * 1024 * 1024);
                container.setDefaultMaxSessionIdleTimeout(1 * 60 * 60 * 1000L);
                container.connectToServer(this, endpointURI);
                XTickUtil.sleepSeconds(3);
            } catch (Exception e) {
                System.err.println("Failed to connect to WebSocket server" + e.getMessage());
                XTickUtil.sleepSeconds(60);
                connectToServer(endpointURI, reason);
            }
        } else {
            System.exit(0);
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to WebSocket server");
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("Disconnected from WebSocket server. Reason: " + reason.toString());
        connectToServer(endpointURI, reason);
    }

    @OnMessage
    public void onMessage(String data) {
        System.out.println("Received message: " + data);
    }

    @OnMessage
    public void onMessage(byte[] data) {
        XTickUtil.processData(data, dataConsumer);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("WebSocket连接发生错误.error=" + throwable.getMessage());
    }


    public void exec() {
        taskThreadPool.execute(() -> {
            while (true) {
                try {
                    CommonPacket packet = queue.take();//处理业务逻辑....
                    System.out.println(JsonUtil.toJson( packet));
                    System.out.println(String.format("%s,received  data.[market=%s,type=%s,period=%s,size=%s]", LocalDateTime.now().format(formatter), packet.getMarket(), packet.getType(), packet.getPeriod(), packet.getData() instanceof List ? ((List) packet.getData()).size() : (packet.getData() instanceof Map ? ((Map) packet.getData()).size() : 0)));
//                    if (data instanceof TickPacket) {
//                        TickPacket packet = (TickPacket) data;
//                        long tickTime = new ArrayList<>(packet.getData().values()).get(0).getTime();
//                        System.out.println(String.format("%s,received tick data.time=%s秒,[authCode=%s,type=%s,period=%s,size=%s]", LocalDateTime.now().format(formatter), (System.currentTimeMillis() - tickTime) / 1000, packet.getAuthCode(), packet.getType(), packet.getPeriod(), packet.getData().size()));
//                        System.out.println(packet);
//                    } else {
//                        MinutePacket packet = (MinutePacket) data;
//                        System.out.println(String.format("%s,received minute data.[authCode=%s,type=%s,period=%s,size=%s]", LocalDateTime.now().format(formatter), packet.getAuthCode(), packet.getType(), packet.getPeriod(), packet.getData().size()));
//                        System.out.println(packet);
//                    }
                } catch (Exception e) {
                    System.err.println("Failed to process data." + e.getMessage());
                }
            }
        });
    }

    /**
     * 测试订阅行情数据推送功能入口
     * <p>
     * authCodes参数解释
     * 订阅类别 period.market.type  tick.SH.1
     * period代表周期，可取枚举值如下：tick time   代表tick数据和K线数据
     * market代表市场，可取枚举值如下：SZ SH BJ HK 代表深交所、上交所、北交所、港交所
     * type代表数据类型，可取枚举值如下：1 3 10 20  代表沪深京A股type=1，港股type=3，沪深指数type=10，沪深ETF type=20;
     * <p>
     * 最后，总结，大家关注以下枚举值即可
     * <p>
     * - 000001.SZ - 订阅股票tick数据，按个数订阅。
     * - bid.1 - 订阅沪深京A股集合竞价期间竞价数据。
     * - quant.1 - 订阅沪深京A股量化因子数据，数据字段参考《3.7 量化指标接口》。
     * - tick.SZ.1 - 订阅深交所A股的tick数据。
     * - tick.SZ.10 - 订阅深交所指数的tick数据。
     * - tick.SZ.20 - 订阅深交所ETF的tick数据。
     * - tick.SH.1 - 订阅上交所A股的tick数据。
     * - tick.SH.10 - 订阅上交所指数的tick数据。
     * - tick.SH.20 - 订阅上交所ETF的tick数据。
     * - tick.BJ.1 - 订阅北交所ETF的tick数据。
     * - tick.HK.3 - 订阅港交所ETF的tick数据。
     * - minute.SZ.1 - 订阅深交所A股的1分钟k线数据，推送频率为实时。
     * - minute.SZ.10 - 订阅深交所指数的1分钟k线数据，推送频率为实时。
     * - minute.SZ.20 - 订阅深交所ETF的1分钟k线数据，推送频率为实时。
     * - minute.SH.1 - 订阅上交所A股的1分钟k线数据，推送频率为实时。
     * - minute.SH.10 - 订阅上交所指数的1分钟k线数据，推送频率为实时。
     * - minute.SH.20 - 订阅上交所ETF的1分钟k线数据，推送频率为实时。
     * - minute.BJ.1 - 订阅北交所A股的1分钟k线数据，推送频率为实时。
     * - minute.HK.3 - 订阅港交所港股的1分钟k线数据，推送频率为实时。
     * - time.SZ.1 - 订阅深交所A股的1分钟k线数据，推送频率为一分钟。
     * - time.SH.1 - 订阅上交所A股的1分钟k线数据，推送频率为一分钟。
     * - time.BJ.1 - 订阅北交所A股的1分钟k线数据，推送频率为一分钟。
     * - time.HK.3 - 订阅港交所港股的1分钟k线数据，推送频率为一分钟。
     *
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        //List<String> authCodes = ImmutableList.of("000001.SZ", "600000.SH","00001.HK","920001.BJ","000001.SH","510300.SH");
        //List<String> authCodes = ImmutableList.of("bid.1","tick.SZ.1", "tick.SZ.10", "tick.SZ.20", "time.SZ.1", "tick.SH.1", "tick.SH.10", "tick.SH.20", "time.SH.1", "tick.BJ.1", "time.BJ.1", "tick.HK.3", "time.HK.3");
        List<String> authCodes = ImmutableList.of("minute.SZ.1");//新用户，可以订阅北交所的tick行情数据
        String user = URLEncoder.encode(JsonUtil.toJson(TickSubcribeInfo.builder().token(XTickConst.token).authCodes(authCodes).build()), StandardCharsets.UTF_8.toString());
        XTickWebSocketClient wsClient = new XTickWebSocketClient(URI.create(String.format("ws://ws.xtick.top/ws/%s", user)));
        wsClient.exec();
    }
}
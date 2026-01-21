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

@ClientEndpoint
public class XTickWebSocketClient {
    private URI endpointURI;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private LinkedBlockingQueue<CommonPacket> queue = new LinkedBlockingQueue<>(200000);
    private ThreadPoolExecutor taskThreadPool = new ThreadPoolExecutor(3, 3, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1000), new ThreadFactoryBuilder().setNameFormat("xtick-task-%d").build());

    private Consumer<String> dataConsumer = result -> {
        CommonPacket packet;
        if (StringUtils.isNotBlank(result)) {
            packet = JsonUtil.jsonToObj(result, CommonPacket.class);//可以根据实际订阅数据，选择TickPacket或者MinutePacket类型反序列化
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
                container.setDefaultMaxBinaryMessageBufferSize(5 * 1024 * 1024);
                container.setDefaultMaxTextMessageBufferSize(512 * 1024);
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
                    System.out.println(String.format("%s,received  data.[market=%s,type=%s,period=%s,size=%s]", LocalDateTime.now().format(formatter), packet.getMarket(), packet.getType(), packet.getPeriod(), packet.getData() instanceof List ? ((List) packet.getData()).size() : (packet.getData() instanceof Map ? ((Map) packet.getData()).size() : 0)));
                } catch (Exception e) {
                    System.err.println("Failed to process data." + e.getMessage());
                }
            }
        });
    }

    /**
     * WebSocket客户端，用于连接XTick的WebSocket服务。
     * 官网：http://www.xtick.top/
     * 订阅数据按照证券交易所订阅推送，包括上交所、深交所、北交所、港交所（只支持部分股票）。
     * 数据为实时推送，发数据非常快，客户端接受到数据后，最好做异步处理，将接受数据和数据处理分开，避免接受数据阻塞。切记...切记...切记：数据接受和数据处理，务必放在两个线程中，不要阻塞数据接受。
     * <p>
     * authCodes参数解释
     * 订阅类别 period.market.type  tick.SH.1
     * period代表数据类别，可取枚举值如下：tick bid   代表tick数据和竞价数据
     * market代表市场，可取枚举值如下：SZ SH BJ HK 代表深交所、上交所、北交所、港交所
     * type代表数据类型，可取枚举值如下：1 3 10 20  代表沪深京A股type=1，港股type=3，沪深指数type=10，沪深ETF type=20;
     * <p>
     * 最后，总结，大家关注以下枚举值即可
     * - 000001.SZ - 订阅股票tick数据，按个数订阅。推送频率为实时。
     * - bid.1 - 订阅沪深京A股集合竞价期间竞价数据。推送频率为实时。
     * - quant.data.1 - 订阅沪深京A股量化因子数据，数据字段参考《量化指标接口》。推送频率一分钟
     * - quant.time.1 - 订阅沪深京A股量化因子数据。推送频率为实时
     * - tick.SZ.1 - 订阅深交所A股的tick数据。推送频率为实时，联系客服开通。
     * - tick.SZ.10 - 订阅深交所指数的tick数据。推送频率为实时，联系客服开通。
     * - tick.SZ.20 - 订阅深交所ETF的tick数据。推送频率为实时，联系客服开通。
     * - tick.SH.1 - 订阅上交所A股的tick数据。推送频率为实时，联系客服开通。
     * - tick.SH.10 - 订阅上交所指数的tick数据。推送频率为实时，联系客服开通。
     * - tick.SH.20 - 订阅上交所ETF的tick数据。推送频率为实时，联系客服开通。
     * - tick.BJ.1 - 订阅北交所ETF的tick数据。推送频率为实时，联系客服开通。
     * - tick.HK.3 - 订阅港交所ETF的tick数据。推送频率为实时，联系客服开通。
     * - minute.SZ.1 - 订阅深交所A股的1分钟k线数据，推送频率为实时。
     * - minute.SZ.10 - 订阅深交所指数的1分钟k线数据，推送频率为实时。
     * - minute.SZ.20 - 订阅深交所ETF的1分钟k线数据，推送频率为实时。
     * - minute.SH.1 - 订阅上交所A股的1分钟k线数据，推送频率为实时。
     * - minute.SH.10 - 订阅上交所指数的1分钟k线数据，推送频率为实时。
     * - minute.SH.20 - 订阅上交所ETF的1分钟k线数据，推送频率为实时。
     * - minute.BJ.1 - 订阅北交所A股的1分钟k线数据，推送频率为实时。
     * - minute.HK.3 - 订阅港交所港股的1分钟k线数据，推送频率为实时。
     * - kline.1m.1 - 订阅沪深京A股的1分钟k线数据，推送频率为一分钟。
     * - kline.1m.10 - 订阅沪深京指数的1分钟k线数据，推送频率为一分钟。
     * - kline.1m.20 - 订阅沪深京ETF的1分钟k线数据，推送频率为一分钟。
     * - kline.1m.3 - 订阅HK股的1分钟k线数据，推送频率为一分钟。
     *
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        //List<String> authCodes = ImmutableList.of("000001.SZ", "600000.SH","00001.HK","920001.BJ","000001.SH","510300.SH");
        //List<String> authCodes = ImmutableList.of("bid.1","tick.SZ.1", "tick.SZ.10", "tick.SZ.20",  "tick.SH.1", "tick.SH.10", "tick.SH.20", "tick.BJ.1", "tick.HK.3");
        List<String> authCodes = ImmutableList.of("bid.1", "tick.HK.3", "tick.SZ.1", "quant.time.1");//新用户，可以订阅北交所的tick行情数据
        String user = URLEncoder.encode(JsonUtil.toJson(TickSubcribeInfo.builder().token(XTickConst.token).authCodes(authCodes).build()), StandardCharsets.UTF_8.toString());
        XTickWebSocketClient wsClient = new XTickWebSocketClient(URI.create(String.format("ws://ws.xtick.top/ws/%s", user)));
        wsClient.exec();
    }
}
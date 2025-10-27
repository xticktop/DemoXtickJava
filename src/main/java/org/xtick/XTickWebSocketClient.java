package org.xtick;


import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import jakarta.websocket.*;

import org.apache.commons.lang3.StringUtils;
import org.xtick.bean.MinutePacket;
import org.xtick.bean.TickPacket;
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
import java.util.ArrayList;
import java.util.List;
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
 * - tick.SZ - 订阅深交所A股的tick数据。
 * - tick.SH - 订阅上交所A股的tick数据。
 * - tick.BJ - 订阅北交所A股的tick数据。
 * - tick.HK - 订阅港交所港股的tick数据。
 * - 000001.SZ - 订阅深交所平安银行000001的tick数据。支持按股票个数订阅，包括沪深京港四个交易所的股票，最多订阅50个。
 * - time.SZ - 订阅深交所A股的k线数据，包括time、1m。
 * - time.SH - 订阅上交所A股的k线数据，包括time、1m。
 * - time.BJ - 订阅北交所A股的k线数据，包括time、1m。
 * - time.HK - 订阅港交所港股的k线数据，包括time、1m。
 * 入参2：token 登录XTick网站，注册获取
 *
 */
@ClientEndpoint
public class XTickWebSocketClient {
    private URI endpointURI;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>(100000);
    private ThreadPoolExecutor taskThreadPool = new ThreadPoolExecutor(3, 3, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1000), new ThreadFactoryBuilder().setNameFormat("xtick-task-%d").build());

    private Consumer<String> dataConsumer = result -> {
        Object packet;
        if (StringUtils.isNotBlank(result)) {
            if (result.contains("1m")) {
                packet = JsonUtil.jsonToObj(result, MinutePacket.class);
            } else {
                packet = JsonUtil.jsonToObj(result, TickPacket.class);
            }
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
            System.err.println("Failed to connect to WebSocket server" + e.getMessage());
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
        XTickUtil.processData(new ByteArrayInputStream(data), dataConsumer);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("WebSocket连接发生错误.error=" + throwable.getMessage());
    }


    public void exec() {
        taskThreadPool.execute(() -> {
            while (true) {
                try {
                    Object data = queue.take();
                    if (data instanceof TickPacket) { //处理业务逻辑....
                        TickPacket packet = (TickPacket) data;
                        long tickTime = new ArrayList<>(packet.getData().values()).get(0).getTime();
                        System.out.println(String.format("%s,received tick data.time=%s秒,[authCode=%s,period=%s,size=%s]", LocalDateTime.now().format(formatter),(System.currentTimeMillis()-tickTime)/1000, packet.getAuthCode(), packet.getPeriod(), packet.getData().size()));
                    } else {
                        MinutePacket packet = (MinutePacket) data;
                        System.out.println(String.format("%s,received minute data.[authCode=%s,period=%s,size=%s]", LocalDateTime.now().format(formatter), packet.getAuthCode(), packet.getPeriod(), packet.getData().size()));
                    }
                } catch (Exception e) {
                    System.err.println("Failed to process data." + e.getMessage());
                }
            }
        });
    }

    /**
     * 测试订阅行情数据推送功能入口
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        //List<String> authCodes = ImmutableList.of("time.SZ", "time.SH", "time.BJ", "time.HK", "tick.SZ", "tick.SH", "tick.BJ", "tick.HK");
        List<String> authCodes = ImmutableList.of("tick.SZ", "tick.HK");//新用户，可以订阅北交所的tick行情数据
        String user = URLEncoder.encode(JsonUtil.toJson(TickSubcribeInfo.builder().token(XTickConst.token).authCodes(authCodes).build()), StandardCharsets.UTF_8.toString());
        XTickWebSocketClient wsClient = new XTickWebSocketClient(URI.create(String.format("ws://ws.xtick.top/ws/%s", user)));
        wsClient.exec();
    }
}
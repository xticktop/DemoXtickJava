package org.xtick;


import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import jakarta.websocket.*;
import org.apache.catalina.util.StringUtil;
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
                XTickUtil.sleepSeconds(30);
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
                        System.out.println(String.format("%s,received tick data.[authCode=%s,period=%s,size=%s]", LocalDateTime.now().format(formatter), packet.getAuthCode(), packet.getPeriod(), packet.getData().size()));
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

    public static void main(String[] args) throws UnsupportedEncodingException {
        //List<String> authCodes = ImmutableList.of("time.SZ", "time.SH", "time.BJ", "time.HK", "tick.SZ", "tick.SH", "tick.BJ", "tick.HK");
        List<String> authCodes = ImmutableList.of("time.SZ","tick.BJ");//新用户，可以订阅北交所的tick行情数据
        String user = URLEncoder.encode(JsonUtil.toJson(TickSubcribeInfo.builder().token(XTickConst.token).authCodes(authCodes).build()), StandardCharsets.UTF_8.toString());
        XTickWebSocketClient wsClient = new XTickWebSocketClient(URI.create(String.format("ws://ws.xtick.top/ws/%s", user)));
        wsClient.exec();
    }

}

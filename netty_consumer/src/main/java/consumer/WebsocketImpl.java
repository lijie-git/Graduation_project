package consumer;

import com.aimango.utils.PropertiesUtils;
import com.alibaba.fastjson.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.websocket.WebSocketUtils;

import java.net.URI;
import java.net.URISyntaxException;

public class WebsocketImpl extends WebSocketClient {
    public WebSocketClient webSocketClient;
    private NettyConsumer nettyConsumer;
    private static final Logger logger = LoggerFactory.getLogger(WebsocketImpl.class);
    int count = 0;


    public WebsocketImpl(URI serverUri, Draft protocolDraft, NettyConsumer nettyConsumer) {
        super(serverUri, protocolDraft);
        this.nettyConsumer = nettyConsumer;
    }


    @Override

    public void onOpen(ServerHandshake handshakedata) {
        WebSocketUtils.status = true;
        JSONObject checkObject = new JSONObject();//验证json
        logger.info("++++++++netty连接成功!!++++++");
    }

    @Override
    public void onMessage(String message) {
        logger.info("+++++++++服务器返回的消息++++++++++: " + message);
        WebSocketUtils.onMessageHandler(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        WebSocketUtils.status = false;
        logger.info("+++++++++++++++++++[websocket] 退出连接+++++++++++++++++++" + code + reason + remote);
        nettyConsumer.webSocketClient.close();
        ThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    nettyConsumer.webSocketClient = new WebsocketImpl(new URI("ws://localhost:8088/ws"), new Draft_6455(), nettyConsumer);
                    logger.info("++++++++++连接断掉,正在尝试重新连接+++++++++++++");
                    nettyConsumer.webSocketClient.connect();
                    logger.info("++++++++++已经调用了连接方法正在等待连接结果+++++++++++++");
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onError(Exception ex) {
        logger.info((("+++++++++++++++++++[websocket] 连接错误={}+++++++++++++++++" + ex.getMessage())));
    }
}

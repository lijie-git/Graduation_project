package consumer;

import com.aimango.pojo.wechatService.UserMessage;
import com.aimango.utils.PropertiesUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.pulsar.client.api.*;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.LoginRequest;
import util.websocket.WebSocketUtils;

import java.net.URI;
import java.net.URISyntaxException;


public class NettyConsumer implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(NettyConsumer.class);
    public WebSocketClient webSocketClient;
    private static final String inputTopic = "wechat.netty.topic";

    private void process(String input) {
        logger.info("NettyConsumer开始处理接收到的消息");
//        LoginRequest loginRequest = JSON.parseObject(input, LoginRequest.class);
        JSONObject jsonObject = JSON.parseObject(input);
        JSONObject msgJson = new JSONObject();
        try {
            // WebSocket.READYSTATE readyState = webSocketClient.getReadyState();
//            if (WebSocket.READYSTATE.OPEN == readyState) {
//                if (WebSocketUtils.getFlag()) {
            if (WebSocketUtils.status) {
                //msgJson.put("loginRequest", loginRequest);
                webSocketClient.send(jsonObject.toJSONString());
//                }
                logger.info("服务器发消息成功!!");
//            }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("服务器消息发送失败!" + e);
        }
    }

    @Override
    public void run() {
        logger.info("nettyConsumer启动");
        PulsarClient pulsarClient = PulsarUtil.client();
        logger.info("nettyConsumer的pulsar客户端创建完成");
        String wechatMsgTopic = "TESTTOPIC";
        try {
            webSocketClient = new WebsocketImpl(new URI("ws://localhost:8088/ws"), new Draft_6455(), this);
            webSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        logger.info("消费的topic:" + wechatMsgTopic);
        try {
            Consumer<byte[]> consumer = pulsarClient.newConsumer()
                    .subscriptionName("Consumer")
                    .topic(wechatMsgTopic)
                    .subscriptionType(SubscriptionType.Shared) //共享模式
                    .subscribe();
            while (true) {
                Message<byte[]> message = consumer.receive();
                String msg = new String(message.getData());
                logger.info("nettConsumer收到消息： " + msg);
                process(msg);
                consumer.acknowledge(message);
            }
        } catch (PulsarClientException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadPool.submit(new NettyConsumer());
    }
}

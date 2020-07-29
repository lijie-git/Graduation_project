package util.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketUtils {
    public static boolean status;
    private static volatile boolean flag = false;
    private static final Logger logger = LoggerFactory.getLogger(WebSocketUtils.class);

    public static void onMessageHandler(String message) {
        JSONObject jsonObject = JSON.parseObject(message);
        flag = (boolean) jsonObject.get("isSuccess");
        logger.info("++++++++++++++++isSuccess++++++++++: " + flag);
    }

    public synchronized static boolean getFlag() {
        return flag;
    }

    public synchronized static void setFlag(boolean flag) {
        WebSocketUtils.flag = flag;
    }

    public static void onOpenHandler(JSONObject jsonObject, WebSocketClient webSocketClient) {
        logger.info("+++++++++++++++++验证服务器消息+++++++++: " + jsonObject);
        webSocketClient.send(jsonObject.toJSONString());
    }


}

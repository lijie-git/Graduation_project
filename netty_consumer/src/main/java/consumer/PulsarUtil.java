package consumer;

import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class PulsarUtil {
    private PulsarUtil() {

    }


    private volatile static PulsarClient pulsarClient;
    private volatile static PulsarAdmin pulsarAdmin;

    public static PulsarClient client() {
        if (pulsarClient == null) {
            synchronized (com.aimango.pojo.conversation.PulsarUtil.class) {
                if (pulsarClient == null) {
                    try {
                        pulsarClient = PulsarClient.builder().serviceUrl("pulsar://localhost:6650").build();
                    } catch (PulsarClientException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return pulsarClient;
    }

    public static PulsarAdmin admin() {
        if (pulsarAdmin == null) {
            synchronized (com.aimango.pojo.conversation.PulsarUtil.class) {
                if (pulsarAdmin == null) {
                    try {
                        pulsarAdmin = PulsarAdmin.builder().serviceHttpUrl("pulsar://192.168.1.250:8080").build();
                    } catch (PulsarClientException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return pulsarAdmin;
    }
}

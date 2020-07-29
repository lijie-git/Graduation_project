package com.lj.cn.Bean;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PulsarClientBean {
    @Bean
    public PulsarClient client() {
        String pusalUrl = "pulsar://localhost:6650";
        PulsarClient client = null;
        try {
            client = PulsarClient.builder().serviceUrl(pusalUrl).build();
        } catch (PulsarClientException e) {
            e.printStackTrace();
        }
        return client;
    }
}

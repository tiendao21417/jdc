package com.jdc.onlineshopping.app.ops.service;

import com.jdc.onlineshopping.aop.logging.LoggerProvider;
import com.jdc.onlineshopping.kafka.HeaderData;
import com.jdc.onlineshopping.kafka.KafkaTransferData;
import com.jdc.onlineshopping.utils.JsonSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author tiendao on 22/07/2021
 */
@Service
public class OpsKafkaServiceImpl implements OpsKafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topics.transfer}")
    private String topic;

    public OpsKafkaServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(Object message, String requestId, String key) {

        KafkaTransferData transferData = new KafkaTransferData();
        transferData.setData(JsonSupport.toJson(message));
        transferData.setHeader(new HeaderData());
        transferData.getHeader().setKey(key);
        transferData.getHeader().setRequestId(requestId);
        sendToKafkaInternal(JsonSupport.toJson(transferData));
    }

    private void sendToKafkaInternal(String message) {

        LoggerProvider.APP.info(String.format("Start send data to kafka: [%s]", message));
        if (message == null || message.isEmpty()) {
            return;
        }
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult result) {
                LoggerProvider.APP.info("[PUBLIC] [PRODUCER-WORKER] Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable e) {
                LoggerProvider.APP.error("[PUBLIC] [PRODUCER-WORKER] Unable to send message=["
                        + message + "] due to : " + e.getMessage());
            }
        });
    }
}

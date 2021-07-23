package com.jdc.onlineshopping.app.ops.service;

public interface OpsKafkaService {

    void send(Object message, String requestId, String key);
}

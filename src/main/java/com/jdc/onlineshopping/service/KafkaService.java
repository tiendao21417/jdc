package com.jdc.onlineshopping.service;

public interface KafkaService {

    void send(Object message, String requestId, String key);
}

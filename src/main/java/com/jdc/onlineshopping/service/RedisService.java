package com.jdc.onlineshopping.service;

public interface RedisService {

    String get(String key, Object... args);

    String set(String key, String value, Object... args);
}

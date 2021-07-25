package com.jdc.onlineshopping.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author tiendao on 23/07/2021
 */
@Component
public class ConfigData {

    @Value("${redis.host}")
    public String redisHost;

    @Value("${redis.port}")
    public int redisPort;
}

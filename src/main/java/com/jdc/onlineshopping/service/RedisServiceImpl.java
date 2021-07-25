package com.jdc.onlineshopping.service;

import com.jdc.onlineshopping.aop.logging.LoggerProvider;
import com.jdc.onlineshopping.config.ConfigData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * @author tiendao on 18/07/2021
 */
@Service
public class RedisServiceImpl implements RedisService {

    private final JedisPool jedisPool;

    private static final int DefaultMaxIdle = 5;
    private static final int DefaultMinIdle = 1;
    private static final int DefaultMaxTotal = 20;
    private static final int DefaultMaxWaitMillis = 30000;

    private final ConfigData configData;

    public RedisServiceImpl(ConfigData configData) {

        this.configData = configData;
        JedisPoolConfig redisConfig = new JedisPoolConfig();
        redisConfig.setMaxIdle(DefaultMaxIdle);
        redisConfig.setMinIdle(DefaultMinIdle);
        redisConfig.setMaxTotal(DefaultMaxTotal);
        redisConfig.setMaxWaitMillis(DefaultMaxWaitMillis);

        LoggerProvider.APP.info(String.format("Connecting to Redis: %s, port: %s, db: %s%n",
                this.configData.redisHost, this.configData.redisPort,
                Protocol.DEFAULT_DATABASE));
        this.jedisPool = new JedisPool(redisConfig, this.configData.redisHost, this.configData.redisPort,
                Protocol.DEFAULT_TIMEOUT, null,
                Protocol.DEFAULT_DATABASE);
    }

    @Override
    public String get(String key, Object... args) {

        String fullKey = String.format(key, args);
        try (Jedis jedis = getJedis()) {
            return jedis.get(fullKey);
        }
    }

    @Override
    public String set(String key, String value, Object... args) {

        String fullKey = String.format(key, args);
        LoggerProvider.APP.info(String.format("Start store redis [%s] : [%s]", fullKey, value));
        try (Jedis jedis = getJedis()) {
            return jedis.set(fullKey, value);
        }
    }

    public Jedis getJedis() {
        return this.jedisPool.getResource();
    }
}

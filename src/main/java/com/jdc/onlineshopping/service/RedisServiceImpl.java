package com.jdc.onlineshopping.service;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

/**
 * @author tiendao on 18/07/2021
 */
@Service
public class RedisServiceImpl implements RedisService {

    private JedisPool jedisPool;
}

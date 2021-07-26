package com.jdc.onlineshopping.service;

import com.jdc.onlineshopping.config.ConfigData;
import com.jdc.onlineshopping.mapper.UserMapperImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author tiendao on 25/07/2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisServiceImpl.class, ConfigData.class})
public class RedisServiceTest {

    @Autowired
    private ConfigData configData;

    @Autowired
    private RedisService redisService;

    @Test
    public void getSet() {
        String key = "user.key1";
        String value = "user.value1";
        redisService.set(key, value);
        String resultValue = redisService.get(key);
        assertThat(value).isEqualTo(resultValue);
    }
}

package com.sqli.ecommerce.cart.common.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisPoolFactory {

    @Value("${redis.host}")
    private String REDIS_HOSTNAME;

    @Value("${redis.port}")
    private int REDIS_PORT;

    @Bean
    public JedisPool jedisPoolFactory(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);
        config.setMaxTotal(10);
        config.setMaxWaitMillis(3 * 1000);
        return new JedisPool(config,REDIS_HOSTNAME,REDIS_PORT,
            3*1000,null,0);
    }
}


package com.sqli.ecommerce.cart.common.redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqli.ecommerce.cart.common.prefix.KeyPrefix;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service @Transactional @Slf4j
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    @Autowired
    ObjectMapper objectMapper;

    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) throws JsonProcessingException {
        Jedis jedis = new Jedis();
        try {
            jedis = jedisPool.getResource();
            String realKey=prefix.getPrefix() + key;
            String str = jedis.get(realKey);

            T t = stringToBean(str,clazz);
            return t;
        }finally {
            returnToPool(jedis);
        }
    }

    public <T> Boolean set(KeyPrefix prefix,String key,T value) throws JsonProcessingException {
        Jedis jedis = new Jedis();
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <=0){
                return false;
            }
            String realKey = prefix.getPrefix() + key;
            int seconds = prefix.getExpireSeconds();
            if (seconds <= 0 ){
                jedis.set(realKey,str);
            }else {
                jedis.setex(realKey,seconds,str);
            }
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    public boolean delete(KeyPrefix prefix,String key){
        Jedis jedis = new Jedis();
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            long ret = jedis.del(realKey);
            return ret>0;
        }finally {
            returnToPool(jedis);
        }
    }

    public <T> boolean exists(KeyPrefix prefix,String key){
        Jedis jedis = new Jedis();
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    public boolean existsValue(KeyPrefix prefix,String key,String field){
        Jedis jedis = new Jedis();
        try {
            jedis = jedisPool.getResource();
            String realkey = prefix.getPrefix() + key;
            Boolean result = jedis.hexists(realkey,field);
            return result;
        }finally {
            returnToPool(jedis);
        }
    }

    public <T> Long incr(KeyPrefix prefix,String key){
        Jedis jedis = new Jedis();
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            return jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    public <T> Long decr(KeyPrefix prefix,String key){
        Jedis jedis = new Jedis();
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            return jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    public <T> String hget(KeyPrefix prefix,String key,String filed){
        Jedis jedis = new Jedis();
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            return jedis.hget(realKey,filed);
        }finally {
            returnToPool(jedis);
        }
    }

    public<T> Long hset(KeyPrefix prefix,String key, String field, String value){
        Jedis jedis = new Jedis();
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            int seconds = prefix.getExpireSeconds();

            long hsetResult = jedis.hset(realKey,field,value);
            log.info("expiration time: "+seconds);
            if (seconds > 0 ) {
                Long expireResult = jedis.expire(realKey, seconds);
            }
            return hsetResult;
        }finally {
            returnToPool(jedis);
        }

    }

    public List<String> hvals(KeyPrefix prefix,String key){
        Jedis jedis = new Jedis();
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            return jedis.hvals(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    public Long hdel(KeyPrefix prefix,String key,String field){
        Jedis jedis = new Jedis();
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            return jedis.hdel(realKey,field);
        }finally {
            returnToPool(jedis);
        }
    }



    public static <T> String beanToString(T value) throws JsonProcessingException {
        if (value ==null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz ==int.class || clazz ==Integer.class){
            return String.valueOf(value);
        }else if(clazz ==long.class || clazz == Long.class){
            return String.valueOf(value);
        }else if (clazz ==String.class){
            return (String) value;
        }else{
            return new ObjectMapper().writeValueAsString(value);
        }
    }

    public static <T> T stringToBean(String str,Class<T> clazz) throws JsonProcessingException {
        if (str == null || str.length() <=0 || clazz==null){
            return null;
        }
        if (clazz ==int.class || clazz == Integer.class){
            return (T) Integer.valueOf(str);
        }else if(clazz == long.class || clazz ==Long.class){
            return (T) Long.valueOf(str);
        }else if (clazz == String.class){
            return (T) str;
        }else {
            return new ObjectMapper().readValue(str,clazz);

        }
    }


    private void returnToPool(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }
}

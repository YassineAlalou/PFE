package com.sqli.ecommerce.cart.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqli.ecommerce.cart.common.prefix.CartPrefix;
import com.sqli.ecommerce.cart.common.prefix.KeyPrefix;
import com.sqli.ecommerce.cart.common.redis.RedisService;
import com.sqli.ecommerce.cart.dto.CartDto;
import com.sqli.ecommerce.cart.model.OrderItem;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartService {

    @Inject
    RedisService redisService;

    @Inject
    ObjectMapper mapper;

    @Inject
    KeyPrefix keyPrefix;

    public int addOrderItem(String customerId, OrderItem orderItem) {
        String productId = String.valueOf(orderItem.getProductId());

        Boolean exists = redisService.existsValue(keyPrefix,customerId,productId);
        try {
            if (exists){
                String strData = redisService.hget(keyPrefix,customerId,productId);
                if (strData !=null){
                    OrderItem parsedItem = mapper.readValue(strData, OrderItem.class);
                    parsedItem.setQuantity(orderItem.getQuantity());
                    redisService.hset(new CartPrefix(),customerId,productId, mapper.writeValueAsString(parsedItem));
                }else {
                    return 0;
                }
                return 1;
            }
            redisService.hset(keyPrefix,customerId,productId, mapper.writeValueAsString(orderItem));
        } catch (JsonProcessingException e) {
            log.error("From CartService.addOrderItem!", e);
            throw new RuntimeException(e.getMessage()+" check logs for more details");
        }
        return 1;
    }

    public CartDto getCart(String userId){

        List<String> jsonList = redisService.hvals(keyPrefix,userId);
        List<OrderItem> orderItems = jsonList.stream()
            .map(item -> fromStringToT(item,OrderItem.class)).collect(Collectors.toList());

        CartDto cartDto = new CartDto();
        cartDto.setCustomerId(Integer.parseInt(userId));
        cartDto.setOrderProducts(orderItems);

        return cartDto;
    }

    public int updateOrderItemQuantity(String userId, int productId, int num){
        String json = redisService.hget(keyPrefix,userId,String.valueOf(productId));
        if (json==null){
            return 0;
        }
        OrderItem orderItem = null;
        try {
            orderItem = mapper.readValue(json, OrderItem.class);
            orderItem.setQuantity(num);
            redisService.hset(keyPrefix,userId,String.valueOf(productId), mapper.writeValueAsString(orderItem));
        } catch (JsonProcessingException e) {
            log.error("Error converting OrderItem to String", e);
            throw new RuntimeException(e.getMessage()+" check logs for more details");
        }
        return 1;
    }

    public int delCartProduct(String userId, int productId) {
        redisService.hdel(new CartPrefix(),userId,String.valueOf(productId));
        return 1;
    }

    public int delCart(String userId) {
        redisService.delete(keyPrefix,userId);
        return 1;
    }


    private <T> T fromStringToT(String item, Class<T> objectClass) {
        try {
            return new ObjectMapper().readValue(item, objectClass);
        } catch (JsonProcessingException e) {
            log.error("Error converting String to object",e);
            throw new RuntimeException("Error converting String to"+objectClass.toString()+", "+e.getMessage());
        }
    }
}

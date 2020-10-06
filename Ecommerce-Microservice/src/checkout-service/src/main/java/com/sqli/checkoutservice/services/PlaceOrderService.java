package com.sqli.checkoutservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sqli.checkoutservice.dtos.cart_orders.CartOrderDTO;
import com.sqli.checkoutservice.exceptions.PlaceOrderFailedException;

@Service
public class PlaceOrderService {

    @Value("${order.url}")
    private String orderUrl;

    @Value("${cart.url}")
    private String cartUrl;

    private final Logger logger;

    private final RestTemplate restTemplate;

    public PlaceOrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        logger = LoggerFactory.getLogger(PaymentPreOrderService.class);
    }

    public CartOrderDTO PlaceOrderCart(CartOrderDTO cartOrderDTO){
        CartOrderDTO order=createOrder(cartOrderDTO);
        if (order!=null)
           deleteCachedCart(cartOrderDTO.getCustomerId());
        return order;
    }

    private CartOrderDTO createOrder(CartOrderDTO cartOrder){
        logger.info("Place Order");
        String ordersUrl=orderUrl+"/orders";
        logger.info("ordersUrl :"+ordersUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CartOrderDTO> requestEntity = new HttpEntity<>(cartOrder, headers);
        ResponseEntity<CartOrderDTO> response = restTemplate.exchange(ordersUrl, HttpMethod.POST, requestEntity,CartOrderDTO.class);
        if (!response.getStatusCode().equals(HttpStatus.CREATED))
            throw new PlaceOrderFailedException("failed to place Order");
        return response.getBody();
    }

    private Boolean deleteCachedCart(Long customerId){
        logger.info("delete CachedCart");
        String cartsUrl=cartUrl+"/carts?customer="+customerId;
        logger.info("CartsUrl :"+cartsUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<Object> response=restTemplate.exchange(cartsUrl, HttpMethod.DELETE,new HttpEntity<>(headers), Object.class);
        return response.getStatusCode().equals(HttpStatus.NO_CONTENT);
    }

}

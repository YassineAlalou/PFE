package com.sqli.ecommerce.cart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sqli.ecommerce.cart.common.prefix.CartPrefix;
import com.sqli.ecommerce.cart.common.prefix.KeyPrefix;

@Configuration
public class BeansConfig {

    @Bean
    public KeyPrefix getKeyPrefix(){
        return new CartPrefix();
    }
}

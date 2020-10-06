package com.sqli.checkoutservice.config.beans;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BeansConfig {
    @Bean
    public RestTemplate rest(RestTemplateBuilder builder) {
        return builder.build();
    }

}
package com.sqli.ecommerce.cart.common.prefix;

import org.springframework.beans.factory.annotation.Value;

public abstract class BasePrefix implements KeyPrefix {

    @Value("${expire}")
    private int expireSeconds;

    private String prefix;

    public BasePrefix(String prefix){
        this.prefix=prefix;
        //this.expireSeconds=3600;
    }

    @Override
    public int getExpireSeconds(){
        return expireSeconds;
    }

    @Override
    public String getPrefix(){
        String className = getClass().getSimpleName();
        return className+":"+prefix;
    }
}

package com.sqli.ecommerce.cart.dto;

import java.util.List;

import com.sqli.ecommerce.cart.model.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CartDto {
    private Number customerId;
    private List<OrderItem> orderProducts;
}

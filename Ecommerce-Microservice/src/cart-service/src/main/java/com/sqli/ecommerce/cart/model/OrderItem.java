package com.sqli.ecommerce.cart.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class OrderItem implements Serializable {

    private int productId;
    private int quantity;
    private Double price;
}

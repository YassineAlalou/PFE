package com.sqli.checkoutservice.dtos.cart_orders;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartOrderDTO {
    private String orderId;
    private Long customerId;
    private List<OrderItemDTO> orderProducts;
    public double CalculeTotal(){
        return orderProducts.stream().mapToDouble(
            orderItemDTO -> orderItemDTO.getQuantity() * orderItemDTO.getPrice()).sum();
    }
}

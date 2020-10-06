package com.sqli.ecommerce.shipping.dto.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public final class AddShipmentDto implements Serializable {

    private final String orderId;

    private final String email;

    private final String address;


}

package com.sqli.ecommerce.shipping.dto.dtos;

import java.io.Serializable;

import com.sqli.ecommerce.shipping.entity.ShipmentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public final class ShipmentStatusDto implements Serializable {
    private final Long id;
    private final ShipmentStatus status;
}

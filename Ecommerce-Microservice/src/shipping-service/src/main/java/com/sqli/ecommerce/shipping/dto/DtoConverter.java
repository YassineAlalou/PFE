package com.sqli.ecommerce.shipping.dto;

import com.sqli.ecommerce.shipping.dto.dtos.ShipmentStatusDto;
import com.sqli.ecommerce.shipping.entity.Shipment;

public class DtoConverter {

    private DtoConverter(){

    }
    public static ShipmentStatusDto toShipmentStatusDto(Shipment shipment){
        return new ShipmentStatusDto(
            shipment.getId(),
            shipment.getStatus()
        );
    }

}

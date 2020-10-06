package com.sqli.ecommerce.shipping.controller;



import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqli.ecommerce.shipping.dto.dtos.AddShipmentDto;
import com.sqli.ecommerce.shipping.dto.dtos.ShipmentStatusDto;
import com.sqli.ecommerce.shipping.entity.Shipment;
import com.sqli.ecommerce.shipping.service.ShippingService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/shipping")
public class ShippingController {
    @Inject
    ShippingService shippingService;

    @GetMapping("/status/{id}")
    @ApiOperation(value = "Get Shipment status by Shipment id.")
    public ShipmentStatusDto getStatus(@PathVariable Long id){
        return shippingService.getStatus(id);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Shipment by id.")
    public Shipment getShipment(@PathVariable Long id){
        return shippingService.getShipment(id);
    }

    @PostMapping @ApiOperation(value = "Add new shipment.")
    public Shipment addShipment(@RequestBody AddShipmentDto addShipmentDto){
        return shippingService.addShipment(addShipmentDto);
    }

    @PutMapping("/status/change/{id}") @ApiOperation(value = "Update the shipment status by id.")
    public ShipmentStatusDto changeStatus (@PathVariable Long id){
        return shippingService.changeStatus(id);
    }
}

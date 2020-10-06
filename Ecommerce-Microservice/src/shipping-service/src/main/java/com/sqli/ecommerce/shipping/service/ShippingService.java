package com.sqli.ecommerce.shipping.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sqli.ecommerce.shipping.exception.ReceivedStatusException;
import com.sqli.ecommerce.shipping.messaging.MessageSender;
import com.sqli.ecommerce.shipping.dao.ShippingRepository;
import com.sqli.ecommerce.shipping.dto.DtoConverter;
import com.sqli.ecommerce.shipping.dto.dtos.AddShipmentDto;
import com.sqli.ecommerce.shipping.dto.dtos.ShipmentStatusDto;
import com.sqli.ecommerce.shipping.entity.Shipment;
import com.sqli.ecommerce.shipping.entity.ShipmentStatus;
import com.sqli.ecommerce.shipping.exception.DataNotFoundException;

@Service
public class ShippingService {

    @Inject
    private ShippingRepository shippingRepository;

    @Inject
    private MessageSender messageSender;

    public Shipment addShipment(AddShipmentDto addShipmentDto){
        Shipment shipment = new Shipment(null,
            addShipmentDto.getOrderId(),
            addShipmentDto.getEmail(),
            addShipmentDto.getAddress(),
            ShipmentStatus.CREATED
        );
        Shipment result = shippingRepository.save(shipment);
        messageSender.sendShipmentCreatedMessage(result.getEmail(), result.getId());
        return result;
    }

    public ShipmentStatusDto getStatus(Long id){
        Shipment shipment = shippingRepository.findById(id)
            .orElseThrow(() -> new DataNotFoundException("Shipment not found with id: "+id));
        return DtoConverter.toShipmentStatusDto(shipment);
    }

    public ShipmentStatusDto changeStatus(Long id){
        Shipment shipment = shippingRepository.findById(id)
            .orElseThrow(() -> new DataNotFoundException("Shipment not found with id: "+id));
        handleChangeStatus(shipment);
        shipment = shippingRepository.save(shipment);
        messageSender.sendStatusModifiedMessage(shipment.getEmail(), shipment.getId(), shipment.getStatus());
        return DtoConverter.toShipmentStatusDto(shippingRepository.save(shipment));
    }

    public Shipment getShipment(Long id) {
        return shippingRepository.findById(id)
            .orElseThrow(() -> new DataNotFoundException("Shipment not found with id: "+id));
    }

    private void handleChangeStatus(Shipment shipment) {
        if(shipment.getStatus().equals(ShipmentStatus.RECEIVED))
            throw new ReceivedStatusException("Shipment is already in RECEIVED status!");

        if(shipment.getStatus().equals(ShipmentStatus.CREATED))
            shipment.setStatus(ShipmentStatus.PACKED);

        else if(shipment.getStatus().equals(ShipmentStatus.PACKED))
            shipment.setStatus(ShipmentStatus.SENDING);

        else if(shipment.getStatus().equals(ShipmentStatus.SENDING))
            shipment.setStatus(ShipmentStatus.RECEIVED);
    }

}

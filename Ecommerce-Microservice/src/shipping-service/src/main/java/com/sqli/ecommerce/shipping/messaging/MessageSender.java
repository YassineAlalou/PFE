package com.sqli.ecommerce.shipping.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sqli.ecommerce.shipping.entity.ShipmentStatus;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageSender {

    @Value("${exchange}")
    private String RABBITMQ_EXCHANGE;

    @Value("${routing_key}")
    private String RABBITMQ_ROUTING_KEY;

    private final String MODIFICATION_SUBJECT="Your shipment Status is Modified";
    private final String CREATION_SUBJECT="Shipment created";

    public RabbitTemplate rabbitTemplate;

    public MessageSender(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendShipmentCreatedMessage(String email, Long shipmentId){
        String message = buildShipmentCreatedMessage(email, shipmentId);
        handleConvertAndSend(message);
    }

    private String buildShipmentCreatedMessage(String email, Long shipmentId) {
        return new StringBuilder().append(email).append(";")
            .append(CREATION_SUBJECT)
            .append(";")
            .append("Hello,")
            .append("\n")
            .append("He have created a shipment with id: ")
            .append(shipmentId)
            .append(". it will be send soon! ")
            .append("\n")
            .append("Thanks for your trust.")
            .toString();
    }

    public void sendStatusModifiedMessage(String email, Long shipmentId, ShipmentStatus shipmentStatus){
        String message = buildStatusModifiedMessage(email, shipmentId, shipmentStatus);
        handleConvertAndSend(message);
    }

    private String buildStatusModifiedMessage(String email, Long shipmentId, ShipmentStatus shipmentStatus) {
        return new StringBuilder().append(email).append(";")
            .append(MODIFICATION_SUBJECT).append(";")
            .append("Hello,").append("\n")
            .append("Your shipment id: ").append(shipmentId).append(" is modified to ").append(shipmentStatus).append(".").append("\n")
            .append("Thanks for your trust.")
            .toString();
    }

    private void handleConvertAndSend(String message) {
        rabbitTemplate.convertAndSend(
            RABBITMQ_EXCHANGE,
            RABBITMQ_ROUTING_KEY,
            message
        );
        log.info("Message sent : "+message);
    }
}

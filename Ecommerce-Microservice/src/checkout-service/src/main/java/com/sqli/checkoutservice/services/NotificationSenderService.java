package com.sqli.checkoutservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sqli.checkoutservice.dtos.NotificationDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationSenderService {

    @Value("${rabbitmq.exchange}")
    private String RABBITMQ_EXCHANGE;

    @Value("${rabbitmq.routing_key}")
    private String RABBITMQ_ROUTING_KEY;

    private final String SUBJECT="You Order was passed";

    private final Logger logger;

    public RabbitTemplate rabbitTemplate;

    public NotificationSenderService( RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        this.logger = LoggerFactory.getLogger(PaymentPreOrderService.class);
    }

    public void SendNotification(NotificationDTO notification){
        logger.info("start building Notification");
        String message = this.buildNotificationMessage(notification);
        rabbitTemplate.convertAndSend(
            RABBITMQ_EXCHANGE,
            RABBITMQ_ROUTING_KEY,
            message
        );
        log.info("Message sended: "+message);
    }

    private String buildNotificationMessage(NotificationDTO notification) {
        return new StringBuilder().append(notification.getEmail()).append(";")
            .append(SUBJECT).append(";")
            .append("Hello,").append("\n")
            .append("Your order id: ").append(notification.getOrderId())
            .append("was succefully passed ").append(".").append("\n")
            .append("Thanks you, for your trust.")
            .toString();
    }
}



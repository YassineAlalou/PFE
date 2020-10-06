package com.sqli.ecommerce.notification.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiver {

    @Autowired
    EmailSender emailSender;

    Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    public void receiveMessage(String message) {
        logger.info("Received <" + message + ">");
        String[] data = getData(message);
        emailSender.sendEmail(data[0],data[1],data[2]);
    }

    private String[] getData(String message) {
        String[] data = message.split(";",3);
        if(data.length!=3)
            throw new RuntimeException("Message not supported.");
        return data;
    }

}

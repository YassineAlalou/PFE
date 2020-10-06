package com.sqli.ecommerce.notification.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    Logger logger = LoggerFactory.getLogger(EmailSender.class);

    @Value("${mail.username}")
    private String sendFrom;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    public void sendEmail(String sendTo,String subject, String message) {
        logger.info("Receiver: "+sendTo); logger.info("Subject: "+subject); logger.info("Message: "+message);
        Properties props = buildProperties();
        Session session = getSession(props);
        try {
            Message mail = buildMail(session, sendTo, subject, message);
            Transport.send(mail);
            logger.info("Sent mail successfully....");
        } catch (MessagingException e) {
            logger.error("Cannot send message",e);
        }
    }

    private Message buildMail(Session session, String sendTo, String subject, String message) throws MessagingException {
        Message mail = new MimeMessage(session);
        mail.setFrom(new InternetAddress(sendFrom));
        mail.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(sendTo));
        mail.setSubject(subject);
        mail.setText(message);
        return mail;
    }

    private Session getSession(Properties props) {
        return Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
    }

    private Properties buildProperties() {
        Properties props =new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        return props;
    }

}

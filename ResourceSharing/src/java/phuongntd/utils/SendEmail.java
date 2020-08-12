package phuongntd.utils;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yun
 */
public class SendEmail implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(SendEmail.class.getName());

    public static void sendEmail(String email, String hashKey) {

        String emailOwner = "Your email here";
        String password = "Your password";
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailOwner, password);
            }

        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailOwner));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Email Verification Link");
            message.setText("Verification Link :: " + "http://localhost:8084/ResourceSharing/verifyAccount?key1=" + email + "&key2=" + hashKey);

            //send the message  
            Transport.send(message);
        } catch (MessagingException ex) {
            LOGGER.error("SendEmail_MessagingException " + ex.getMessage());
        }
    }

}

package edu.jhu.sample.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailUtilGmail {

    public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML)
            throws MessagingException {
        
        // 1 - get a mail session
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth", "true");
        javax.mail.Authenticator authenticator = new javax.mail.Authenticator()
            {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() 
                {
                return new javax.mail.PasswordAuthentication("apljhu@gmail.com", "Jocelyn78");
            }
        };
        Session session = Session.getDefaultInstance(props, authenticator);
        session.setDebug(true);

        // 2 - create a message
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }

        // 3 - address the message
        Address fromAddress = new InternetAddress(from);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        // 4 - send the message
        Transport transport = session.getTransport();
        transport.connect();
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
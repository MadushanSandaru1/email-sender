package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    public static void main(String[] args) {

        // Set SMTP server properties
        Properties properties = new Properties();

        properties.setProperty("mail.smtp.host", Const.HOST);
        properties.setProperty("mail.smtp.port", Const.PORT);
        properties.setProperty("mail.smtp.user", Const.USER);
        properties.setProperty("mail.smtp.password", Const.PASSWORD);
        properties.setProperty("mail.smtp.socketFactory.port", Const.PORT);
        properties.setProperty("mail.smtp.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.debug", "false");
        properties.setProperty("mail.smtp.from", Const.USER);

        properties.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.EnableSSL.enable","false");

        // Set sender and recipient addresses
        String senderEmail = Const.SENDER_EMAIL;
        String senderPassword = Const.SENDER_PASSWORD;
        String recipientEmail = Const.RECIPIENT_EMAIL;

        // Create a mail session with SMTP authentication
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = createMimeMessage();
            // Send message
            Transport.send(message);
            System.out.println("Message sent successfully.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public MimeMessage createMimeMessage() {
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(senderEmail));
            message.setHeader("Content-Type", "text/html");

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set Subject: header field
            message.setSubject("This is Email subject");

            // Set the actual message
            message.setText("This is Email body");

        return message;
    }
}

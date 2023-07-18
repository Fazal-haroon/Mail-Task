package com.example.demo.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    public static void main(String[] args) {
        String recipientEmail = "iqrar.kpk1000@gmail.com";
        String senderEmail = "ae7a5b2f651981";
        String senderPassword = "af25e386ea6b45";

        try {
            sendEmail(recipientEmail, senderEmail, senderPassword);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Reason: " + e.getMessage());
        }
    }

    public static void sendEmail(String recipientEmail, String senderEmail, String senderPassword) throws MessagingException {
        // SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "sandbox.smtp.mailtrap.io"); // Replace with your SMTP server address
        properties.put("mail.smtp.port", "2525"); // Replace with the SMTP server port

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        // Create a MimeMessage
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject("Test Email");
        message.setText("This is a test email sent using Java SMTP.");

        // Send the email
        Transport.send(message);
    }
}

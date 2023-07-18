package com.example.demo.util;

import com.example.demo.config.ConfigurationProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;

@Service
public class EmailSenderUtil {

    private static final Logger log = LoggerFactory.getLogger(EmailSenderUtil.class);

    @Autowired
    private ConfigurationProperty configurationProperty;

    @Value("${spring.profiles.active}")
    private String enviornmentValue;

    public void sendEmail(String toEmailAddress, String Subject, String messageContent) {
        Properties prop = setSystemProperties();
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(configurationProperty.getUsername(),
                        configurationProperty.getPassword());
            }
        });
        try {
            System.out.println("<---------Sending email--------->");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(configurationProperty.getFrom()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress));
            message.setSubject(Subject);
            message.setText(messageContent);

            Transport.send(message);

            log.info("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmail(List<String> toEmailAddress, String Subject, String messageContent) {

        Properties prop = setSystemProperties();
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(configurationProperty.getUsername(),
                        configurationProperty.getPassword());
            }
        });
        try {
            System.out.println("<---------Sending email--------->");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(configurationProperty.getFrom()));

            InternetAddress[] toAddresses = new InternetAddress[toEmailAddress.size()];
            int count = 0;
            for (String email : toEmailAddress) {
                toAddresses[count] = new InternetAddress(email);
                count++;
            }
            message.setRecipients(Message.RecipientType.TO, toAddresses);
            message.setSubject(Subject);
            message.setText(messageContent);

            // Transport.send(message);
            for (String email : toEmailAddress) {
                log.info("Email sent successfully  to" + email);
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailWithAttachement(String toEmailAddress, String Subject, String messageContent,
                                         String fileName) {

        Properties prop = setSystemProperties();
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(configurationProperty.getUsername(),
                        configurationProperty.getPassword());
            }
        });
        try {
            System.out.println("<---------Sending email--------->");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(configurationProperty.getFrom()));

//			InternetAddress[] toAddresses = new InternetAddress[toEmailAddress.size()];
//			int count = 0;
//			for (String email : toEmailAddress) {
//				toAddresses[count] = new InternetAddress(email);
//				count++;
//			}
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress));
            message.setSubject(Subject);

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(messageContent, "text/html");
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(fileName);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);

            multipart.addBodyPart(messageBodyPart);
            // Send the complete message parts
            message.setContent(multipart);

            Transport.send(message);
            // for (String email : toEmailAddress) {
            log.debug("Email sent successfully  to: " + toEmailAddress);
            // }

        } catch (Exception e) {
            log.error("Error in sending email to : " + toEmailAddress, e);
            e.printStackTrace();
        }
    }

    public void sendEmailWithHTMLContent(List<String> toEmailAddress, String Subject, String messageContent) {

        Properties prop = setSystemProperties();
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(configurationProperty.getUsername(),
                        configurationProperty.getPassword());
            }
        });
        try {
            System.out.println("<---------Sending email--------->");
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(configurationProperty.getFrom()));

            InternetAddress[] toAddresses = new InternetAddress[toEmailAddress.size()];
            int count = 0;
            for (String email : toEmailAddress) {
                toAddresses[count] = new InternetAddress(email);
                count++;
            }
            message.setRecipients(Message.RecipientType.TO, toAddresses);
            message.setSubject(Subject);
            message.setContent(messageContent, "text/html; charset=utf-8");

            Transport.send(message);
            for (String email : toEmailAddress) {
                log.info("Email sent successfully  to " + email);
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Properties setSystemProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", configurationProperty.getHost());
        prop.put("mail.smtp.port", configurationProperty.getPort());
        prop.put("mail.smtp.auth", configurationProperty.isSmtpAuth());
        prop.put("mail.smtp.starttls.enable", configurationProperty.isSmtpStarttlsenable()); // TLS
        int timeout = 10000; // 10 seconds
        prop.put("mail.smtp.timeout", String.valueOf(timeout));
        prop.put("mail.smtp.connectiontimeout", String.valueOf(timeout));
        prop.put("mail.debug", "true");
        if(enviornmentValue.equalsIgnoreCase("dev")) {
            prop.put("mail.smtp.starttls.required", configurationProperty.isSmtpSSLRequiredAuth());
            prop.put("mail.smtp.ssl.protocols", configurationProperty.getSmtpSSLprotocols());
            prop.put("mail.smtp.ssl.trust", configurationProperty.getHost());
        }
        return prop;
    }
}

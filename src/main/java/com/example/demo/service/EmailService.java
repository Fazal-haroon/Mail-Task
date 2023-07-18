package com.example.demo.service;

import com.example.demo.entity.Recipient;
import com.example.demo.exception.CustomException;
import com.example.demo.repository.RecipientRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Service
@Log4j2
public class EmailService {

    @Autowired
    RecipientRepository recipientRepo;

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmails() {
        // Get all recipients that haven't been sent yet
        List<Recipient> recipients = recipientRepo.findBySent(false);
        for (Recipient recipient : recipients) {
            try {
                // Send email
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(username);
                message.setTo(recipient.getEmail());
                message.setText(recipient.getBody());
                message.setSubject(recipient.getSubject());

                javaMailSender.send(message);
                recipient.setSent(true);
                recipientRepo.save(recipient);
            } catch (Exception e) {
                throw new CustomException("Fail", "Blocked", 404);
            }
        }
    }
}

/*
* SMTPTransport t = (SMTPTransport)session.getTransport("smtp");
* t.connect();
* t.sendMessage(message,message.getAllRecipients());
* String response = t.getLastServerResponse();
* boolean s = t.getReportSuccess();
* int code = t.getLastReturnCode();
* return response;
* */

package com.example.demo.service;

import com.example.demo.entity.Recipient;
import com.example.demo.exception.CustomException;
import com.example.demo.repository.RecipientRepository;
import com.example.demo.util.EmailSenderUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Service
@Log4j2
public class EmailService {

    @Autowired
    RecipientRepository recipientRepo;

    @Autowired
    private EmailSenderUtil emailSenderService;

    public void sendEmails() {
        List<Recipient> recipients = recipientRepo.findBySent(false);
        List<String> emailList = List.of("iqrar.kpk1000@gmail.com");
            emailSenderService.sendEmail(emailList, "recipient.getSubject()", "recipient.getBody()");
            emailSenderService.sendEmail("iqrar.kpk1000@gmail.com", "recipient.getSubject()", "recipient.getBody()");
            emailSenderService.sendEmailWithHTMLContent(emailList, "recipient.getSubject()", "recipient.getBody()");
//            emailSenderService.sendEmailWithAttachement("iqrar.kpk1000@gmail.com", "recipient.getSubject()", "recipient.getBody()","recipient.getBody()");
    }
}

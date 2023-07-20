package com.example.demo.service;

import com.example.demo.entity.Recipient;
import com.example.demo.repository.RecipientRepository;
import com.example.demo.util.EmailSenderUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Log4j2
public class EmailService {

    @Autowired
    RecipientRepository recipientRepo;

    @Autowired
    private EmailSenderUtil emailSenderService;

    List<Recipient> recipients;

    public EmailService(RecipientRepository recipientRepo, EmailSenderUtil emailSenderService) {
        this.recipientRepo = recipientRepo;
        this.emailSenderService = emailSenderService;
    }

    @Transactional
    public void sendEmails() {
        List<Long> userIdList = new ArrayList<>();
        Set<String> emailList = new HashSet<>();
        recipients = recipientRepo.findBySent(false);
        recipients.stream().forEach(recipient -> {
            try {
                emailSenderService.sendEmail(recipient.getEmail(), recipient.getSubject(), recipient.getBody());
                userIdList.add(recipient.getId());
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
        //Update Query
        recipientRepo.updateFlagForUsers(userIdList);
        Set<String> results = emailSenderService.getResults();
        results.stream().forEach(element -> {
            emailList.add(element);
        });
        //search query
        List<Recipient> recipients1 = recipientRepo.searchByEmail(emailList);
        recipients = recipients1;
    }
}

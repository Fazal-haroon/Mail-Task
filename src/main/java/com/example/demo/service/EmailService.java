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

    private List<Recipient> masterRecipients;

    private List<Long> userIdList;

    private Set<String> emailList;

    public EmailService(RecipientRepository recipientRepo, EmailSenderUtil emailSenderService) {
        this.recipientRepo = recipientRepo;
        this.emailSenderService = emailSenderService;
        this.userIdList = new ArrayList<>();
        this.emailList = new HashSet<>();
    }

    @Transactional
    public void sendEmails() {
        loadMasterRecipients();
        processRecipients();
    }

    @Transactional
    public void resendEmails() {
        loadMasterRecipients();
        processRecipients();
    }

    private void loadMasterRecipients() {
        masterRecipients = recipientRepo.findBySent(false);
    }

    private void processRecipients() {
        masterRecipients.forEach(recipient -> {
            try {
                emailSenderService.sendEmail(recipient.getEmail(), recipient.getSubject(), recipient.getBody());
                userIdList.add(recipient.getId());
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
        if (userIdList.size() > 0) {
            // Bulk update the flag for sent recipients
            recipientRepo.updateFlagForUsers(userIdList);
            userIdList.clear();
        }

        Set<String> failEmailList = emailSenderService.getFailEmailList();
        emailList.addAll(failEmailList);
        if (failEmailList.size() > 0) {
            // Search for recipients based on failed emails
            masterRecipients = recipientRepo.searchByEmail(emailList);
            emailList.clear();
        }
    }
}
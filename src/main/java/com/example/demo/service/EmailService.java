package com.example.demo.service;

import com.example.demo.entity.Recipient;
import com.example.demo.repository.RecipientRepository;
import com.example.demo.util.EmailSenderUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class EmailService {

    @Autowired
    RecipientRepository recipientRepo;

    @Autowired
    private EmailSenderUtil emailSenderService;

    private List<Recipient> masterRecipients;

    @Autowired
    private CacheManager cacheManager;

    private List<Recipient> recipientsToUpdate; // List to store recipients that need to be updated in the database

    public EmailService(RecipientRepository recipientRepo, EmailSenderUtil emailSenderService) {
        this.recipientRepo = recipientRepo;
        this.emailSenderService = emailSenderService;
        this.recipientsToUpdate = new ArrayList<>();
    }

    @Cacheable("masterRecipients")
    public List<Recipient> getMasterRecipients() {
        return recipientRepo.findBySent(false);
    }

    @Transactional
    public void sendEmails() {
        loadMasterRecipients();
        processRecipients();
        bulkUpdateRecipients(); // Bulk update the recipients at the end
    }

    @Transactional
    public void resendEmails() {
        loadMasterRecipients();
        processRecipients();
        bulkUpdateRecipients(); // Bulk update the recipients at the end
    }

    private void loadMasterRecipients() {
        masterRecipients = getMasterRecipients();
    }

    private void processRecipients() {
        for (Recipient recipient : masterRecipients) {
            try {
                emailSenderService.sendEmail(recipient.getEmail(), recipient.getSubject(), recipient.getBody());
                removeRecipientFromCache(recipient);
                recipientsToUpdate.add(recipient); // Add to the update list
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void bulkUpdateRecipients() {
        List<Long> userIdList = recipientsToUpdate.stream()
                .map(Recipient::getId)
                .collect(Collectors.toList());

        if (!userIdList.isEmpty()) {
            recipientRepo.updateFlagForUsers(userIdList); // Perform the bulk update
        }

        // Clear the recipientsToUpdate list after the bulk update
        recipientsToUpdate.clear();
    }

    public void removeRecipientFromCache(Recipient recipient) {
        Cache masterRecipientsCache = cacheManager.getCache("masterRecipients");
        if (masterRecipientsCache != null) {
            Object key = recipient.getId();
            masterRecipientsCache.evict(key);
        } else {
            log.warn("masterRecipients cache not found!");
        }
    }
}

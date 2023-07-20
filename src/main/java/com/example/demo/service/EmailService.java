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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
public class EmailService {

    @Autowired
    RecipientRepository recipientRepo;

    @Autowired
    private EmailSenderUtil emailSenderService;

    @Autowired
    private CacheManager cacheManager;

    private List<Recipient> masterRecipients;

    private List<Recipient> recipientsToUpdate; // List to store recipients that need to be updated in the database

    private Set<String> emailList;

    public EmailService() {
        this.recipientsToUpdate = new ArrayList<>();
        this.emailList = new HashSet<>();
    }

//    @Cacheable("masterRecipients")
//    public List<Recipient> getMasterRecipients() {
//        return recipientRepo.findBySent(false);
//    }

    @Transactional
    public void sendEmails() {
        loadMasterRecipients();
        processRecipients();
        bulkUpdateRecipients(); // Bulk update the recipients at the end
    }

    @Transactional
    public void resendEmails() {
        // Check if master recipients are already cached
        if (masterRecipients == null) {
            // If not cached, load recipients from the database
            loadMasterRecipients();
        }
        processRecipients();
        bulkUpdateRecipients(); // Bulk update the recipients at the end
    }

    private void loadMasterRecipients() {
        // Check if master recipients are already cached
        Cache masterRecipientsCache = cacheManager.getCache("masterRecipients");
        if (masterRecipientsCache != null) {
            // Retrieve master recipients from the cache
            Cache.ValueWrapper valueWrapper = masterRecipientsCache.get("recipients");
            if (valueWrapper != null) {
                masterRecipients = (List<Recipient>) valueWrapper.get();
                return;
            }
        }
        // If not cached, fetch recipients from the database
        masterRecipients = recipientRepo.findBySent(false);
        // Cache the master recipients for future use
        if (masterRecipientsCache != null) {
            masterRecipientsCache.put("recipients", masterRecipients);
        }
    }

    private void processRecipients() {
        List<Recipient> recipientsCopy = new ArrayList<>(masterRecipients);

        recipientsCopy.forEach(recipient -> {
            try {
                emailSenderService.sendEmail(recipient.getEmail(), recipient.getSubject(), recipient.getBody());
                recipientsToUpdate.add(recipient);

                // Remove the recipient from the cache after sending the email successfully
                removeRecipientFromCache(recipient);
            } catch (MessagingException e) {
                log.error("Error sending email to {}: {}", recipient.getEmail(), e.getMessage(), e);
            }
        });
        Set<String> failEmailList = emailSenderService.getFailEmailList();
        emailList.addAll(failEmailList);
        if (!failEmailList.isEmpty()) {
            // Search for recipients based on failed emails
            masterRecipients = recipientRepo.searchByEmail(emailList);
            emailList.clear();
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

    // Method to remove a recipient from the cache
    public void removeRecipientFromCache(Recipient recipient) {
        Cache masterRecipientsCache = cacheManager.getCache("masterRecipients");
        if (masterRecipientsCache != null) {
            // Retrieve the cached master recipients
            Cache.ValueWrapper valueWrapper = masterRecipientsCache.get("recipients");
            if (valueWrapper != null) {
                List<Recipient> cachedRecipients = (List<Recipient>) valueWrapper.get();
                if (cachedRecipients != null) {
                    // Remove the specified recipient from the cached list
                    cachedRecipients.remove(recipient);
                    masterRecipientsCache.put("recipients", cachedRecipients);
                }
            }
        }
    }
}

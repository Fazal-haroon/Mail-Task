package com.example.demo.service;

import com.example.demo.entity.Recipient;
import com.example.demo.repository.RecipientRepository;
import com.example.demo.util.EmailSenderUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Log4j2
public class EmailService {

    @Autowired
    private RecipientRepository recipientRepo;

    @Autowired
    private EmailSenderUtil emailSenderService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Cacheable(value = "masterRecipientCache", key = "'masterRecipient'")
    public List<Recipient> getAllDataFromMySQLAndCacheInRedis() {
        log.info("<------getAllDataFromMySQLAndCacheInRedis() Method Called----->");
        // Fetch all data from MySQL
        List<Recipient> recipientList = recipientRepo.findAll();
        // Save the data in Redis with a cache duration of 1 day
        redisTemplate.opsForValue().set("masterRecipient", recipientList, 1, TimeUnit.DAYS); // Cache for 1 day
        return recipientList;
    }

    @CacheEvict(value = "masterRecipientCache", key = "'masterRecipient'")
    public void evictDataFromCache(Recipient recipient) {
        // This method is used to manually evict the recipient data from the cache
        // after successfully sending an email to the recipient
        log.info("Evicting recipient with ID: {} from the cache.", recipient.getId());
    }

    @Cacheable(value = "masterRecipientCache", key = "'masterRecipient'")
    public List<Recipient> getAllDataFromRedisCache(boolean sentStatus) {
        log.info("<------getAllDataFromRedisCache() Method Called----->");
        // Retrieve the entire list of recipients from the cache
        List<Recipient> recipientList = (List<Recipient>) redisTemplate.opsForValue().get("masterRecipient");

        // If cache is empty, fetch data from MySQL and cache it
        if (recipientList == null || recipientList.isEmpty()) {
            recipientList = getAllDataFromMySQLAndCacheInRedis();
        }
        // Filter the recipient list based on the sent status
        return recipientList.stream()
                .filter(recipient -> recipient.isSent() == sentStatus)
                .collect(Collectors.toList());
    }

    public void sendEmails() {
        log.info("<------sendEmails() Method Called----->");
        List<Recipient> recipientsToSend = getAllDataFromRedisCache(false); // Use Redis cache data
        processRecipients(recipientsToSend);
    }

    public void setRecipientsAsSent(Recipient recipients) {
        log.info("setRecipientsAsSent() Method Called");
        recipients.setSent(true);
        // Update the record in Redis cache
        updateRecipientInCacheWithoutUpdatingDatabase(recipients);
    }

    public void updateRecipientInCacheWithoutUpdatingDatabase(Recipient updatedRecipient) {
        log.info("updateRecipientInCacheWithoutUpdatingDatabase() Method Called");
        List<Recipient> recipientList = getAllDataFromRedisCache(false);

        // Find the recipient in the list and update its properties
        Optional<Recipient> recipientToUpdate = recipientList.stream()
                .filter(recipient -> recipient.getId().equals(updatedRecipient.getId()))
                .findFirst();

        if (recipientToUpdate.isPresent()) {
            Recipient recipient = recipientToUpdate.get();
            recipient.setEmail(updatedRecipient.getEmail());
            recipient.setSubject(updatedRecipient.getSubject());
            recipient.setBody(updatedRecipient.getBody());
            recipient.setSent(updatedRecipient.isSent());

            // Update the entire list in Redis cache
            redisTemplate.opsForValue().set("masterRecipient", recipientList, 1, TimeUnit.DAYS); // Cache for 1 day
        } else {
            log.warn("Recipient with ID {} not found in Redis cache.", updatedRecipient.getId());
        }
    }

    public void resendEmails() {
        log.info("<------resendEmails() Method Called----->");
        Set<String> failEmailList = emailSenderService.getFailEmailList();
        // Filter recipients and collect recipients whose email addresses match the failed email addresses
        List<Recipient> failedRecipients = getAllDataFromRedisCache(false).stream()
                .filter(recipient -> failEmailList.contains(recipient.getEmail()))
                .collect(Collectors.toList());
        // Now you have a list of failedRecipients containing recipients with failed emails
        // You can resend the emails for these recipients
        processRecipients(failedRecipients);
    }

    private void processRecipients(List<Recipient> recipientsToSend) {
        log.info("<------processRecipients() Method Called----->");
        recipientsToSend.forEach(recipient -> {
            try {
                emailSenderService.sendEmail(recipient.getEmail(), recipient.getSubject(), recipient.getBody());
                setRecipientsAsSent(recipient);
            } catch (MessagingException e) {
                log.error("Error sending email to {}: {}", recipient.getEmail(), e.getMessage(), e);
            }
        });
    }

}

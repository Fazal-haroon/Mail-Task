package com.example.demo.service;

import com.example.demo.entity.Recipient;
import com.example.demo.repository.RecipientRepository;
import com.example.demo.util.EmailSenderUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
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
        // Try to retrieve all data from Redis first
        List<Recipient> recipientList = retrieveAllDataFromRedis();

        // If cache is empty, fetch data from MySQL and cache it in Redis
        if (recipientList == null || recipientList.isEmpty()) {
            log.info("Fetching data from MySQL and caching it in Redis.");
            recipientList = recipientRepo.findAll();

            recipientList.forEach(recipient -> {
                String hashKey = "recipient:" + recipient.getId();
                redisTemplate.opsForHash().put(hashKey, "email", recipient.getEmail());
                redisTemplate.opsForHash().put(hashKey, "subject", recipient.getSubject());
                redisTemplate.opsForHash().put(hashKey, "body", recipient.getBody());
                redisTemplate.opsForHash().put(hashKey, "sent", recipient.isSent());
                redisTemplate.expire(hashKey, 10, TimeUnit.MINUTES);
                log.info("Redis Data Hash Key: {}", hashKey);
            });
        } else {
            log.info("Data found in Redis cache. Skipping database fetch.");
        }
        return recipientList;
    }

    private List<Recipient> retrieveAllDataFromRedis() {
        List<Recipient> recipients = new ArrayList<>();
        Set<String> hashKeys = redisTemplate.keys("recipient:*");
        hashKeys.forEach(hashKey -> {
            Recipient recipient = new Recipient();
            recipient.setId(Long.valueOf(hashKey.substring("recipient:".length())));
            recipient.setEmail((String) redisTemplate.opsForHash().get(hashKey, "email"));
            recipient.setSubject((String) redisTemplate.opsForHash().get(hashKey, "subject"));
            recipient.setBody((String) redisTemplate.opsForHash().get(hashKey, "body"));
            recipient.setSent((Boolean) redisTemplate.opsForHash().get(hashKey, "sent"));
            recipients.add(recipient);
        });
        return recipients;
    }

    @Cacheable(value = "masterRecipientCache", key = "'masterRecipient'")
    public List<Recipient> getAllDataFromRedisCache(boolean sentStatus) {
        log.info("<------getAllDataFromRedisCache() Method Called----->");
        // Retrieve the entire list of recipients' IDs from the cache
        List<Recipient> recipientList = getAllDataFromMySQLAndCacheInRedis();

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

    public void setRecipientsAsSent(Recipient recipient) {
        log.info("setRecipientsAsSent() Method Called");
        recipient.setSent(true);
        // Update the record in Redis Hash
        updateRecipientInCacheWithoutUpdatingDatabase(recipient);
    }

    @CachePut(value = "masterRecipientCache", key = "'masterRecipient'")
    public void updateRecipientInCacheWithoutUpdatingDatabase(Recipient updatedRecipient) {
        log.info("updateRecipientInCacheWithoutUpdatingDatabase() Method Called");
        String hashKey = "recipient:" + updatedRecipient.getId();

        // Check if the recipient exists in Redis Hash
        if (redisTemplate.opsForHash().hasKey(hashKey, "email")) {
            // Update the recipient properties in Redis Hash
            redisTemplate.opsForHash().put(hashKey, "email", updatedRecipient.getEmail());
            redisTemplate.opsForHash().put(hashKey, "subject", updatedRecipient.getSubject());
            redisTemplate.opsForHash().put(hashKey, "body", updatedRecipient.getBody());
            redisTemplate.opsForHash().put(hashKey, "sent", updatedRecipient.isSent());
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


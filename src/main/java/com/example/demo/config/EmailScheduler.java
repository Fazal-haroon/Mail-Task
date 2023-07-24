package com.example.demo.config;

import com.example.demo.entity.Recipient;
import com.example.demo.service.EmailService;
import com.example.demo.util.EmailSenderUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Log4j2
public class EmailScheduler {

    @Autowired
    EmailService emailService;

    @Autowired
    private EmailSenderUtil emailSenderService;

    private boolean newRecipientsCreated = true; // Set it to true initially to fetch data for the first time

    //@Scheduled(fixedDelay = 2000) // Every 2 seconds
    @Scheduled(cron = "*/2 * * * * *") // Every 2 seconds
    public void processIndividualEmail() {
        if (newRecipientsCreated) {
            List<Recipient> pendingRecipients = emailService.getAllPendingRecipients();
            if (!pendingRecipients.isEmpty()) {
                Recipient recipient = pendingRecipients.get(0);
                // here I will call the MicroSoft 365 Outlook
                emailSenderService.sendEmail(recipient.getEmail(), recipient.getSubject(), recipient.getBody());
                // Send the email
                recipient.setSent(true);
                emailService.updateRecipientSentStatus(recipient);
                Set<String> results = emailSenderService.getFailEmailList();
                // Filter the pending recipients whose email addresses are in the failed email list
                List<Recipient> recipientsToUpdateFlagFalse = pendingRecipients.stream()
                        .filter(recipientFail -> results.contains(recipientFail.getEmail()))
                        .collect(Collectors.toList());
                // Update the sent status and createdAt for the filtered recipients
                recipientsToUpdateFlagFalse.forEach(recipientFails -> {
                    recipientFails.setSent(false);
                    emailService.updateRecipientSentStatus(recipient);
                    newRecipientsCreated = true;
                });
            } else {
                newRecipientsCreated = false; // Set it to false when there are no new recipients in the database
            }
        }
    }

    // Method to set the flag for new recipients
    public void setNewRecipientsCreated(boolean newRecipientsCreated) {
        this.newRecipientsCreated = newRecipientsCreated;
    }
}

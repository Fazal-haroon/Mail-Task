package com.example.demo.service;

import com.example.demo.config.EmailScheduler;
import com.example.demo.dto.RecipientDTO;
import com.example.demo.entity.Recipient;
import com.example.demo.repository.RecipientRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
public class EmailService {

    @Autowired
    private RecipientRepository recipientRepo;

    @Autowired
    private EmailScheduler emailScheduler;

    public void saveRecipient(RecipientDTO recipientDTO) {
        Recipient recipient = new Recipient();
        recipient.setEmail(recipientDTO.getEmail());
        recipient.setSubject(recipientDTO.getSubject());
        recipient.setBody(recipientDTO.getBody());
        recipient.setSent(false);
        recipient.setCreatedAt(LocalDateTime.now());
        recipientRepo.save(recipient);
        // Set the flag to true when a new recipient is saved
        emailScheduler.setNewRecipientsCreated(true);
    }

    @Transactional
    public void updateRecipientSentStatus(Recipient recipient) {
        // Implement the logic to send an email here
        // Set the "sent" flag to true if the email is sent successfully
        LocalDateTime currentTime = LocalDateTime.now();
        recipientRepo.updateRecipientStatusAndCreatedAt(recipient.getId(), recipient.isSent(), currentTime);
    }

    public List<Recipient> getAllPendingRecipients() {
        return recipientRepo.findBySentOrderByCreatedAtAsc(false);
    }

}


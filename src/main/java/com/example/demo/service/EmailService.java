package com.example.demo.service;

//import com.example.demo.config.EmailScheduler;
import com.example.demo.dto.RecipientRequestDTO;
import com.example.demo.dto.RecipientResponseDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.RecipientRepository;
import com.example.demo.util.ListConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class EmailService {

    @Autowired
    private RecipientRepository recipientRepo;
//
//    @Autowired
//    private EmailScheduler emailScheduler;

    public void saveRecipient(RecipientRequestDTO recipientRequestDTO) {
        Recipient recipient = new Recipient();
        recipient.setSubject(recipientRequestDTO.getSubject());
        recipient.setMessageContent(recipientRequestDTO.getMessageContent());

        List<String> toEmail = recipientRequestDTO.getToEmail();
        String stringEmail = ListConverter.listToString(toEmail);
        recipient.setToEmail(stringEmail);

        recipient.setRecietpname(recipientRequestDTO.getRecietpname());
        recipient.setSent(false);
        recipient.setCreatedAt(LocalDateTime.now());
        recipientRepo.save(recipient);
        // Set the flag to true when a new recipient is saved
//        emailScheduler.setNewRecipientsCreated(true);
    }

    @Transactional
    public void updateRecipientSentStatus(Recipient recipient) {
        // Implement the logic to send an email here
        // Set the "sent" flag to true if the email is sent successfully
        LocalDateTime currentTime = LocalDateTime.now();
        recipientRepo.updateRecipientStatusAndCreatedAt(recipient.getId(), recipient.isSent(), currentTime);
    }

    public List<RecipientResponseDTO> getAllPendingRecipients() {
        List<Recipient> bySentOrderByCreatedAtAsc = recipientRepo.findBySentOrderByCreatedAtAsc(false);
        List<RecipientResponseDTO> recipientResponseDTOList = new ArrayList<>();
        // Convert the toEmail string to list for each RecipientResponseDTO
        for (Recipient recipient : bySentOrderByCreatedAtAsc) {
            RecipientResponseDTO recipientResponseDTO = new RecipientResponseDTO();
            String toEmailsString = recipient.getToEmail();
            List<String> toEmailsList = ListConverter.stringToList(toEmailsString);
            recipientResponseDTO.setId(recipient.getId());
            recipientResponseDTO.setSubject(recipient.getSubject());
            recipientResponseDTO.setMessageContent(recipient.getMessageContent());
            recipientResponseDTO.setToEmailList(toEmailsList);
            recipientResponseDTO.setRecietpname(recipient.getRecietpname());
            recipientResponseDTO.setSent(recipient.isSent());
            recipientResponseDTO.setCreatedAt(recipient.getCreatedAt());
            recipientResponseDTOList.add(recipientResponseDTO);
        }
        return recipientResponseDTOList;
    }

    public List<Recipient> getAllPendingRecipientsData() {
        return recipientRepo.findBySentOrderByCreatedAtAsc(false);
    }

}


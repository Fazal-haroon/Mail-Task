package com.example.demo.controller;

import com.example.demo.dto.RecipientRequestDTO;
import com.example.demo.dto.RecipientResponseDTO;
import com.example.demo.entity.Recipient;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/create-recipient")
    public void addRecipient(@RequestBody RecipientRequestDTO recipientRequestDTO) {
        emailService.saveRecipient(recipientRequestDTO);
    }

    @GetMapping("/get-all-recipient")
    public List<RecipientResponseDTO> getAllRecipient(){
        return emailService.getAllPendingRecipients();
    }

    @GetMapping("/get-all-recipient-data")
    public List<Recipient> getAllRecipientData(){
        return emailService.getAllPendingRecipientsData();
    }

}
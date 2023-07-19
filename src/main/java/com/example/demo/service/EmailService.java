package com.example.demo.service;

import com.example.demo.entity.Recipient;
import com.example.demo.repository.RecipientRepository;
import com.example.demo.util.EmailSenderUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<Long> emailId = new ArrayList<>();
        List<String> emailList = new ArrayList<>();
        recipients = recipientRepo.findBySent(false);
        recipients.stream().forEach(recipient -> {
            try {
                emailSenderService.sendEmail(recipient.getEmail(), recipient.getSubject(), recipient.getBody());
                emailId.add(recipient.getId());
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
        //Update Query
        recipientRepo.updateFlagForUsers(emailId);
        Map<String, Boolean> results = emailSenderService.getResults();
        for (Map.Entry<String, Boolean> entry : results.entrySet()) {
            String email = entry.getKey();
            boolean status = entry.getValue();
            emailList.add(email);
        }
        //search query
        List<Recipient> recipients1 = recipientRepo.searchByEmail(emailList);
        recipients = recipients1;
//            emailSenderService.sendEmail(emailList, "recipient.getSubject()", "recipient.getBody()");
//            emailSenderService.sendEmailWithHTMLContent(emailList, "recipient.getSubject()", "recipient.getBody()");
//            emailSenderService.sendEmailWithAttachement("iqrar.kpk1000@gmail.com", "recipient.getSubject()", "recipient.getBody()","recipient.getBody()");

//        List<String> recipients = new ArrayList<>(Collections.nCopies(2, "iqrar.kpk1000@gmail.com"));
//        recipients.forEach(f -> {
//            try {
//                emailSenderService.send("mismailkhan.mardan@gmail.com", f, "Test Subject", "Test Body");
//            } catch (SMTPSendFailedException e) {
//                Address[] invalidAddresses = e.getInvalidAddresses();
//                Address[] validSentAddresses = e.getValidSentAddresses();
//                Address[] validUnsentAddresses = e.getValidUnsentAddresses();
//
//                StringBuilder sb = new StringBuilder();
//                sb.append("Failed to send email to the following recipients:\n");
//                for (Address address : invalidAddresses) {
//                    sb.append("- ").append(address).append("\n");
//                }
//                for (Address address : validUnsentAddresses) {
//                    sb.append("- ").append(address).append("\n");
//                }
//                System.err.println(sb.toString());
//                //Resend the failed email again the following
//            } catch (MessagingException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        System.out.println("Email sent successfully.");
    }
}

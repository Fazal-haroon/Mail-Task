package com.example.demo.service;

import com.example.demo.entity.Recipient;
import com.example.demo.exception.CustomSMTPException;
import com.example.demo.repository.RecipientRepository;
import com.example.demo.util.EmailSenderUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class EmailService {

    @Autowired
    RecipientRepository recipientRepo;

    @Autowired
    private EmailSenderUtil emailSenderService;

    public void sendEmails() throws CustomSMTPException {
        List<Recipient> recipients = recipientRepo.findBySent(false);
        List<String> emailList = List.of("iqrar.kpk1000@gmail.com");
//            emailSenderService.sendEmail(emailList, "recipient.getSubject()", "recipient.getBody()");
            emailSenderService.sendEmail("iqrar2.kpk1000@gmail.com", "recipient.getSubject()", "recipient.getBody()");
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

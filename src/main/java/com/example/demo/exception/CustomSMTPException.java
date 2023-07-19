package com.example.demo.exception;

import lombok.Data;

import javax.mail.MessagingException;

@Data
public class CustomSMTPException extends MessagingException {

    public CustomSMTPException(String message) {
        super(message);
    }

    public CustomSMTPException(String message, Throwable cause) {
        super(message, (Exception) cause);
    }
}


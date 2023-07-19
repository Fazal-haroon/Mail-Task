package com.example.demo.entity;

public class EmailWithFlag {
    private String email;
    private boolean success;

    public EmailWithFlag(String email, boolean success) {
        this.email = email;
        this.success = success;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

package com.example.WishAndFish.dto;

public class DeclinedRegistrationDTO {
    private String userEmail;
    private String message;

    public DeclinedRegistrationDTO() {
    }

    public DeclinedRegistrationDTO(String userEmail, String message) {
        this.userEmail = userEmail;
        this.message = message;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

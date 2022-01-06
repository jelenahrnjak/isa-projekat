package com.example.WishAndFish.dto;

public class ChangePasswordDTO {

    private String email;
    private String oldPassword;
    private String password;
    private String passwordRepeated;

    public ChangePasswordDTO() {
    }

    public ChangePasswordDTO(String email, String password, String passwordRepeated) {
        this.email = email;
        this.password = password;
        this.passwordRepeated = passwordRepeated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}

package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.User;

public class UserDTO {
    private String password;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;


    public UserDTO() {
    }

    public UserDTO(String password, String email, String name, String surname, String phoneNumber) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public UserDTO(User k){
        this.email = k.getEmail();
        this.password = k.getPassword();
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


}

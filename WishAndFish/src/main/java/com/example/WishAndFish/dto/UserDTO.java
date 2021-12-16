package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.User;

public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;


    public UserDTO() {
    }

    public UserDTO(String username, String password, String email, String name, String surname, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public UserDTO(User k){
        this.username = k.getUsername();
        this.password = k.getPassword();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

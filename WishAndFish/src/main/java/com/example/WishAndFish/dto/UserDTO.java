package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.User;

public class UserDTO {
    private Long id;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private AddressDTO address;

    public UserDTO() {
    }

    public UserDTO(Long id, String password, String email, String name, String surname, String phoneNumber, AddressDTO address) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public UserDTO(User k){
        this.email = k.getEmail();
        this.password = k.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}

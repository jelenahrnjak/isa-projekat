package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.LoyaltyCategory;
import com.example.WishAndFish.model.User;

public class UserDTO {
    private Long id;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private AddressDTO address;
    private String roleName;
    private String verificationCode;
    private String loyalityProgram;
    private double points;
    private int discount;
    private double neededPoints;

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

    public UserDTO(User u){
        this.email = u.getEmail();
        this.name = u.getName();
        this.surname = u.getSurname();
        this.phoneNumber = u.getPhoneNumber();
        this.address = new AddressDTO(u.getAddress());
        this.verificationCode = u.getVerificationCode();
        this.roleName = u.getRole().getName();
        this.loyalityProgram = u.getLoyaltyCategory().getName();
        this.discount = u.getLoyaltyCategory().getDiscount();
        this.points = u.getPoints();
        this.neededPoints = u.getLoyaltyCategory().getNeededPointsToNextLevel() - u.getPoints();
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getLoyalityProgram() {
        return loyalityProgram;
    }

    public void setLoyalityProgram(String loyalityProgram) {
        this.loyalityProgram = loyalityProgram;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getNeededPoints() {
        return neededPoints;
    }

    public void setNeededPoints(double neededPoints) {
        this.neededPoints = neededPoints;
    }
}

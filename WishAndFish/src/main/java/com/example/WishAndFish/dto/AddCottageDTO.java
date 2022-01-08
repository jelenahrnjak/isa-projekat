package com.example.WishAndFish.dto;

import java.util.List;

public class AddCottageDTO {

    private String name;
    private String description;
    private AddressDTO address;
    private Double price;
    private String ownerEmail;

    public AddCottageDTO() {
    }

    public AddCottageDTO(String name, String description, AddressDTO address, Double price, String ownerEmail) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.price = price;
        this.ownerEmail = ownerEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}

package com.example.WishAndFish.dto;

import java.util.List;

public class AddCottageDTO {

    private String name;
    private String description;
    private AddressDTO address;
    private String rating;
    private String coverImage;
    private List<RoomDTO> rooms;
    private List<RuleDTO> rules;
    private Double price;
    private String ownerEmail;

    public AddCottageDTO() {
    }

    public AddCottageDTO(String name, String description, AddressDTO address, String rating, String coverImage, List<RoomDTO> rooms, List<RuleDTO> rules, Double price, String ownerEmail) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.rating = rating;
        this.coverImage = coverImage;
        this.rooms = rooms;
        this.rules = rules;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }

    public List<RuleDTO> getRules() {
        return rules;
    }

    public void setRules(List<RuleDTO> rules) {
        this.rules = rules;
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

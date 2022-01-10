package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.FishingAdventure;

public class FishingAdventureDTO {

    private String name;
    private String description;
    private String address;
    private String rating;
    private String price;
    private String coverImage;
    private String instructor;
    private String instructorEmail;

    public FishingAdventureDTO() {
    }

    public FishingAdventureDTO(String name, String description, String address, String rating, String price, String coverImage, String instructor, String instructorName) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.rating = rating;
        this.price = price;
        this.coverImage = coverImage;
        this.instructor = instructor;
        this.instructorEmail = instructorName;
    }

    public FishingAdventureDTO(FishingAdventure f) {
        this.name = f.getName();
        this.description = f.getDescription();
        this.address = f.getAddress().toString();
        this.rating = Double.toString(f.getRating());
        this.price = Double.toString(f.getPricePerHour());
        this.coverImage = f.getCoverImage();
        this.instructor = f.getFishingInstructor().getName() + " " + f.getFishingInstructor().getSurname();
        this.instructorEmail = f.getFishingInstructor().getEmail();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }
}

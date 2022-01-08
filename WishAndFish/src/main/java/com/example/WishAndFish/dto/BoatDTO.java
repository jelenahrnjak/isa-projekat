package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.Boat;

public class BoatDTO {

    private String name;
    private String description;
    private String address;
    private String rating;
    private String coverImage;

    public BoatDTO() {
    }

    public BoatDTO(Boat b){
        this.name = b.getName();
        this.description = b.getDescription();
        this.address= b.getAddress().toString();
        this.rating = Double.toString(b.getRating());
        this.coverImage = b.getCoverImage();
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

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

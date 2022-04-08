package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.*;

public class CottageDTO {

    private String name;
    private String description;
    private String address;
    private String rating;
    private String price;
    private String coverImage;

    public CottageDTO() {
    }

    public  CottageDTO(Cottage c){
        this.name = c.getName();
        this.description = c.getDescription();
        this.address= c.getAddress().toString();
        this.rating = Double.toString(c.getRating());
        this.price = Double.toString(c.getPricePerDay());
        this.coverImage = c.getCoverImage();
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

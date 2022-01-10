package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.Boat;

public class BoadDisplayDTO {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String rating;
    private String price;
    private String coverImage;

    public BoadDisplayDTO() {
    }

    public BoadDisplayDTO(Long id, String name, String description, String address, String rating, String price, String coverImage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.rating = rating;
        this.price = price;
        this.coverImage = coverImage;
    }

    public BoadDisplayDTO(Boat b) {
        this.id = b.getId();
        this.name = b.getName();
        this.description = b.getDescription();
        this.address = b.getAddress().toString();
        this.rating = Double.toString(b.getRating());
        this.price = Double.toString(b.getRating());
        this.coverImage = b.getCoverImage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}

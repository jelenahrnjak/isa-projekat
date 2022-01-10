package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.Cottage;

public class CottageDisplayDTO {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String rating;
    private String coverImage;

    public CottageDisplayDTO() {
    }

    public CottageDisplayDTO(Long id, String name, String description, String address, String rating, String coverImage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.rating = rating;
        this.coverImage = coverImage;
    }

    public CottageDisplayDTO(Cottage c) {
        this.id = c.getId();
        this.name = c.getName();
        this.description = c.getDescription();
        this.address = c.getAddress().toString();
        this.rating = Double.toString(c.getRating());
        this.coverImage = c.getCoverImage();
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


    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}

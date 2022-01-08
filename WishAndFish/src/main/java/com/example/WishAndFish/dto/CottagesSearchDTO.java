package com.example.WishAndFish.dto;

public class CottagesSearchDTO {

    private String name;
    private String address;
    private String price;
    private String rating;
    private String description;

    public CottagesSearchDTO() {
    }

    public CottagesSearchDTO(String name, String address, String price, String rating, String description) {
        this.name = name;
        this.address = address;
        this.price = price;
        this.rating = rating;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

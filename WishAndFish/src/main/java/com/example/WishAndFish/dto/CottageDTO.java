package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CottageDTO {

    private Long id;
    private String name;
    private String description;
    private String address;
    private String rating;
    private String price;
    private String coverImage;
    private int maximumPeople;
    private Boolean isSubscribed;

    public  CottageDTO(Cottage c){
        this.id = c.getId();
        this.name = c.getName();
        this.description = c.getDescription();
        this.address= c.getAddress().toString();
        this.rating = Double.toString(c.getRating());
        this.price = Double.toString(c.getPricePerDay());
        this.coverImage = c.getCoverImage();
        this.maximumPeople = c.getBedsPerRoom() * c.getNumberOfRooms();
    }

    public CottageDTO(String name, String address, String rating, String price){
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.price = price;
    }
}

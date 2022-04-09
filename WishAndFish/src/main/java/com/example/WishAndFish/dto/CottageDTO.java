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

    private String name;
    private String description;
    private String address;
    private String rating;
    private String price;
    private String coverImage;
    private int maximumPeople;

    public  CottageDTO(Cottage c){
        this.name = c.getName();
        this.description = c.getDescription();
        this.address= c.getAddress().toString();
        this.rating = Double.toString(c.getRating());
        this.price = Double.toString(c.getPricePerDay());
        this.coverImage = c.getCoverImage();
        this.maximumPeople = c.getBedsPerRoom();
    }

}

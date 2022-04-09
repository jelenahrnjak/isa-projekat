package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.Boat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoatDTO {

    private String name;
    private String description;
    private String address;
    private String rating;
    private String price;
    private String coverImage;
    private int maximumPeople;

    public BoatDTO(Boat b){
        this.name = b.getName();
        this.description = b.getDescription();
        this.address= b.getAddress().toString();
        this.rating = Double.toString(b.getRating());
        this.price = Double.toString(b.getPricePerHour());
        this.coverImage = b.getCoverImage();
        this.maximumPeople = b.getCapacity();
    }


}

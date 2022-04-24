package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.FishingAdventure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FishingAdventureDTO {

    private Long id;
    private String name;
    private String description;
    private String address;
    private String rating;
    private String price;
    private String coverImage;
    private String instructor;
    private String instructorEmail;
    private int maximumPeople;
    private Boolean isSubscribed;

    public FishingAdventureDTO(FishingAdventure f) {
        this.id = f.getId();
        this.name = f.getName();
        this.description = f.getDescription();
        this.address = f.getAddress().toString();
        this.rating = Double.toString(f.getRating());
        this.price = Double.toString(f.getPricePerHour());
        this.coverImage = f.getCoverImage();
        this.instructor = f.getFishingInstructor().getName() + " " + f.getFishingInstructor().getSurname();
        this.instructorEmail = f.getFishingInstructor().getEmail();
        this.maximumPeople = f.getCapacity();
    }

    public FishingAdventureDTO(String name, String address, String rating, String price){
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.price = price;
    }
}

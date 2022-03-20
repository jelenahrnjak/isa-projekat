package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddBoatDTO {

    private String name;
    private String type;
    private Double length;
    private Integer engineNumber;
    private String enginePower;
    private Double maxSpeed;
    private Address address;
    private String description;
    private Integer capacity;
    private Double pricePerHour;
    private String ownerEmail;



}

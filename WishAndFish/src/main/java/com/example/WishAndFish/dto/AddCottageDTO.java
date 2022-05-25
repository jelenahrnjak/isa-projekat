package com.example.WishAndFish.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCottageDTO {

    private String name;
    private String description;
    private AddressDTO address;
    private Double price;
    private String ownerEmail;
    private String coverImage;
    private Integer numberOfRooms;
    private Integer bedsPerRoom;

}

package com.example.WishAndFish.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EditBoatDTO {
    private Long id;
    private String name;
    private String type;
    private Double length;
    private Integer engineNumber;
    private String enginePower;
    private Double maxSpeed;
    private AddressDTO address;
    private String description;
    private Integer capacity;
    private String cancellationConditions;
    private Double pricePerHour;
}

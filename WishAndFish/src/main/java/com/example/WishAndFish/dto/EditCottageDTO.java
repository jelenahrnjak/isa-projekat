package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.Cottage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditCottageDTO {
    private Long id;
    private String name;
    private String description;
    private AddressDTO address;
    private Double pricePerDay;
    private int numberOfRooms;
    private int bedsPerRoom;

    public EditCottageDTO(Cottage c){
        this.id = c.getId();
        this.name = c.getName();
        this.description = c.getDescription();
        this.address = new AddressDTO(c.getAddress());
        this.pricePerDay = c.getPricePerDay();
        this.numberOfRooms = c.getNumberOfRooms();
        this.bedsPerRoom = c.getBedsPerRoom();
    }
}

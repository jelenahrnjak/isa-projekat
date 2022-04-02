package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.AdditionalService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalServicesDTO {
    public Long id;
    public String name;
    public String price;

    public AdditionalServicesDTO(AdditionalService as){
        this.id = as.getId();
        this.name = as.getName();
        this.price = as.getPrice();
    }

    public AdditionalServicesDTO(String name, String price){
        this.name = name;
        this.price = price;
    }
}

package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.CancellationConditions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancellationCondiionsDTO {

    private Long id;
    private String description;
    private Double price;


    public CancellationCondiionsDTO(CancellationConditions c){
        this.id = c.getId();
        this.description = c.getDescription();
        this.price = c.getPrice();
    }
}

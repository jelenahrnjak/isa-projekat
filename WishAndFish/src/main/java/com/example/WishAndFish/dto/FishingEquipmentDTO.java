package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.FishingEquipment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FishingEquipmentDTO {
    private Long id;
    private String name;

    public FishingEquipmentDTO(FishingEquipment f){
        this.id = f.getId();
        this.name = f.getName();
    }
}

package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BoatDetailDTO {

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
    private Double averageGrade;
    private List<ImageDTO> images = new ArrayList<>();
    private List<RuleDTO> rules = new ArrayList<>();
    private Double pricePerHour;
    private List<NavigationEquipmentDTO> navigationEquipments = new ArrayList<>();
    private List<FishingEquipmentDTO> fishingEquipments = new ArrayList<>();
    private String cancellationConditions;
    private String coverImage;
    private int numberOfRatings;
    private double rating;
    private boolean deleted;


    public BoatDetailDTO(Boat b){
        this.id = b.getId();
        this.name = b.getName();
        this.type = b.getType();
        this.length = b.getLength();
        this.engineNumber = b.getEngineNumber();
        this.enginePower = b.getEnginePower();
        this.maxSpeed = b.getMaxSpeed();
        this.description = b.getDescription();
        this.capacity = b.getCapacity();
        this.averageGrade = b.getAverageGrade();
        this.pricePerHour = b.getPricePerHour();
        this.coverImage = b.getCoverImage();
        this.numberOfRatings = b.getNumberOfRatings();
        this.rating = b.getRating();
        this.deleted = b.isDeleted();
        this.cancellationConditions = b.getCancellationConditions();
    }

}

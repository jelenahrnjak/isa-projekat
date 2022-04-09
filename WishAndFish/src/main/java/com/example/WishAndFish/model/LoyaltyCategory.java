package com.example.WishAndFish.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="loyaltyCategories")
public class LoyaltyCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "level", unique = true, nullable = false)
    private int level;
    @Column(name = "neededPoints",unique = true, nullable = false)
    private int neededPoints;
    @Column(name = "neededPointsToNextLevel",unique = true, nullable = false)
    private int neededPointsToNextLevel;
    @Column(name = "discount", nullable = false)
    private int discount;


}

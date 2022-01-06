package com.example.WishAndFish.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    public LoyaltyCategory() {
    }

    public LoyaltyCategory(String name, int level, int neededPoints, int discount, int neededPointsToNextLevel) {
        this.name = name;
        this.level = level;
        this.neededPoints = neededPoints;
        this.discount = discount;
        this.neededPointsToNextLevel = neededPointsToNextLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNeededPoints() {
        return neededPoints;
    }

    public void setNeededPoints(int neededPoints) {
        this.neededPoints = neededPoints;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getNeededPointsToNextLevel() {
        return neededPointsToNextLevel;
    }

    public void setNeededPointsToNextLevel(int neededPointsToNextLevel) {
        this.neededPointsToNextLevel = neededPointsToNextLevel;
    }
}

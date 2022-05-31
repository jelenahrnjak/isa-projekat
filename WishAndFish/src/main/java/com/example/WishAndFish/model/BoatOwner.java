package com.example.WishAndFish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class BoatOwner extends User{

    @Column(name = "numberOfRatings", nullable = false)
    private int numberOfRatings;

    @Column(name="rating", nullable = false)
    private double rating;

    public BoatOwner() {
    }

    public BoatOwner(String password, String email, String name, String surname, String phoneNumber) {
        super(password, email, name, surname, phoneNumber);
        this.numberOfRatings  = 0;
        this.rating = 0.0;
    }

    public BoatOwner(User u) {
        super(u);
        this.numberOfRatings  = 0;
        this.rating = 0.0;
    }

}

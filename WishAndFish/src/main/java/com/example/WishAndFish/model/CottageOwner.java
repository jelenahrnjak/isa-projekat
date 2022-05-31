package com.example.WishAndFish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CottageOwner extends User{


    @Column(name = "numberOfRatings", nullable = false)
    private int numberOfRatings;

    @Column(name="rating", nullable = false)
    private double rating;

    public CottageOwner() {
    }

    public CottageOwner(String password, String email, String name, String surname, String phoneNumber) {
        super(password, email, name, surname, phoneNumber);
        this.numberOfRatings  = 0;
        this.rating = 0.0;
    }

    public CottageOwner(User u) {

        super(u);
        this.numberOfRatings = 0;
        this.rating = 0.0;
    }
    
}

package com.example.WishAndFish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "fishing_instructors")
public class FishingInstructor extends User{

    @Column(name = "numberOfRatings", nullable = false)
    private int numberOfRatings;

    @Column(name="rating", nullable = false)
    private double rating;

    public FishingInstructor() {
    }

    public FishingInstructor(String password, String email, String name, String surname, String phoneNumber) {
        super(password, email, name, surname, phoneNumber);
        this.numberOfRatings  = 0;
        this.rating = 0.0;
    }

    public FishingInstructor(User u) {
        super(u);
        this.numberOfRatings  = 0;
        this.rating = 0.0;
    }
}

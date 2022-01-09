package com.example.WishAndFish.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fishing_instructors")
public class FishingInstructor extends User{

    @Column(name = "cover_image", unique = false, nullable = false)
    private String coverImage;

    public FishingInstructor() {
    }

    public FishingInstructor(String password, String email, String name, String surname, String phoneNumber) {
        super(password, email, name, surname, phoneNumber);
    }

    public FishingInstructor(User u) {
        super(u);
    }

}

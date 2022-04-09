package com.example.WishAndFish.model;

import javax.persistence.*;

@Entity
@Table(name = "fishing_instructors")
public class FishingInstructor extends User{

    public FishingInstructor() {
    }

    public FishingInstructor(String password, String email, String name, String surname, String phoneNumber) {
        super(password, email, name, surname, phoneNumber);
    }

    public FishingInstructor(User u) {
        super(u);
    }
}

package com.example.WishAndFish.model;

import javax.persistence.Entity;

@Entity
public class BoatOwner extends User{
    public BoatOwner() {
    }

    public BoatOwner(String password, String email, String name, String surname, String phoneNumber) {
        super(password, email, name, surname, phoneNumber);
    }

    public BoatOwner(User u) {
        super(u);
    }

}

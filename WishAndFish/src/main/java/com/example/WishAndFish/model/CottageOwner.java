package com.example.WishAndFish.model;

import javax.persistence.*;

@Entity
public class CottageOwner extends User{


    public CottageOwner() {
    }

    public CottageOwner(String password, String email, String name, String surname, String phoneNumber) {
        super(password, email, name, surname, phoneNumber);
    }

    public CottageOwner(User u) {
        super(u);
    }
    
}

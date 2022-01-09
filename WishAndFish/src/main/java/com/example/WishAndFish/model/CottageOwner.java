package com.example.WishAndFish.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

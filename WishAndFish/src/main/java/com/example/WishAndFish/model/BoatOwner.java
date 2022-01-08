package com.example.WishAndFish.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BoatOwner extends User{
    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Boat> boats = new HashSet<Boat>();
    public BoatOwner() {
    }

    public BoatOwner(String password, String email, String name, String surname, String phoneNumber) {
        super(password, email, name, surname, phoneNumber);
    }

    public BoatOwner(User u) {
        super(u);
    }

}

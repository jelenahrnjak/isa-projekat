package com.example.WishAndFish.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CottageOwner extends User{

    @OneToMany(mappedBy = "cottageOwner", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Cottage> cottages = new HashSet<Cottage>();

    public CottageOwner() {
    }

    public CottageOwner(String password, String email, String name, String surname, String phoneNumber) {
        super(password, email, name, surname, phoneNumber);
    }

    public CottageOwner(User u) {
        super(u);
    }

    public Set<Cottage> getCottages() {
        return cottages;
    }

    public void setCottages(Set<Cottage> cottages) {
        this.cottages = cottages;
    }
}

package com.example.WishAndFish.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client extends User{


    public Client(User u) {
        super(u);
        this.penalties = 0;
    }

    public Client(String password, String email, String name, String surname, String phoneNumber) {
        super(password, email, name, surname, phoneNumber);
        this.penalties = 0;
    }

    @ManyToMany(targetEntity = Cottage.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Cottage> cottageSubscriptions = new HashSet<>();

    @ManyToMany(targetEntity = Boat.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Boat> boatSubscriptions = new HashSet<>();

    @ManyToMany(targetEntity = FishingAdventure.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<FishingAdventure> adventureSubscriptions = new HashSet<>();


    @Column(name = "penalties", nullable = false)
    private Integer penalties;

}



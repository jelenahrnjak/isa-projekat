package com.example.WishAndFish.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client extends User{


    public Client(User u) {
        super(u);
    }

    @ManyToMany(targetEntity = Cottage.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Cottage> cottageSubscriptions;

    @ManyToMany(targetEntity = Boat.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Boat> boatSubscriptions;

    @ManyToMany(targetEntity = FishingAdventure.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<FishingAdventure> adventureSubscriptions;


    @Column(name = "penalties", nullable = false)
    private Integer penalties;

    @ManyToMany(targetEntity = Reservation.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Reservation> cancellations;

}



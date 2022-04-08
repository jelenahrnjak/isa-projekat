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

    @ManyToMany(targetEntity = Cottage.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Cottage> cottageSubscriptions;

    @ManyToMany(targetEntity = Boat.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Boat> boatSubscriptions;

    @ManyToMany(targetEntity = FishingAdventure.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<FishingAdventure> adventureSubscriptions;
}



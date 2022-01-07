package com.example.WishAndFish.model;

import javax.persistence.*;

@Entity
@Table(name = "navigation_equipment")
public class NavigationEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "boat_id", nullable = false)
    private Boat boat;
}

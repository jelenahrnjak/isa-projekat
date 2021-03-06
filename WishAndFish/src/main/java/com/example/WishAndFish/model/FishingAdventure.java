package com.example.WishAndFish.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "fishing_adventures")
public class FishingAdventure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "deleted", unique = false, nullable = false)
    private boolean deleted;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "numberOfRatings", nullable = false)
    private int numberOfRatings;

    @Column(name="rating", nullable = false)
    private double rating;

    @Column(name="coverImage", nullable = false, unique = false)
    private String coverImage;

    @Column(name = "price_per_day", unique = false, nullable = false)
    private Double pricePerDay;

    @Column(name = "description", unique = false, nullable = true)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fishing_instructor_id")
    private FishingInstructor fishingInstructor;

    @OneToOne(targetEntity = Address.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name="capacity", unique = false, nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "fishingAdventure", fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private Set<Appointment> appointments = new HashSet<>();

    public FishingAdventure(Long id, String name, Double rating, Double pricePerDay){
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.pricePerDay = pricePerDay;
    }
}

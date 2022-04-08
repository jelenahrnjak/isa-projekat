package com.example.WishAndFish.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "cottages")
public class Cottage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = false, nullable = true)
    private String name;

    @Column(name = "description", unique = false, nullable = true)
    private String description;

    @Column(name = "price_per_day", unique = false, nullable = true)
    private Double pricePerDay;

    @OneToOne(targetEntity = Address.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "numberOfRatings")
    private int numberOfRatings;

    @Column(name="rating")
    private double rating;

    @Column(name="coverImage")
    private String coverImage;

    @Column(name="number_of_rooms")
    private String numberOfRooms;

    @Column(name="bed_number_per_day")
    private String bedNumberPerRoom;

    @OneToMany(mappedBy = "cottage", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Room> rooms = new HashSet<Room>();

    @OneToMany(mappedBy = "cottage", fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JsonManagedReference
    private Set<Appointment> appointments = new HashSet<Appointment>();

    @OneToMany(mappedBy = "cottage", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Image> images = new HashSet<Image>();

    @OneToMany(mappedBy = "cottage", fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JsonManagedReference
    private Set<Rule> rules = new HashSet<Rule>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cottage_owner_id")
    private CottageOwner cottageOwner;

    @Column(name = "deleted", unique = false, nullable = false)
    private boolean deleted;

    public Cottage() {

        this.rating = 0;
        this.numberOfRatings = 0;
    }

    public Cottage(long id, String name, String description, Double pricePerDay, Address address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.address = address;
        this.rating = 0;
        this.numberOfRatings = 0;
    }



    public Cottage(String name, String description, Double pricePerDay, Address address, int numberOfRatings, double rating, String coverImage, Set<Room> rooms, Set<Appointment> appointments, Set<Image> images, Set<Rule> rules, CottageOwner cottageOwner, boolean deleted) {
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.address = address;
        this.numberOfRatings = numberOfRatings;
        this.rating = rating;
        this.coverImage = coverImage;
        this.rooms = rooms;
        this.appointments = appointments;
        this.images = images;
        this.rules = rules;
        this.cottageOwner = cottageOwner;
        this.deleted = deleted;
    }


    public Cottage(String name, String description, Double pricePerDay, Address address, CottageOwner cottageOwner) {
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.address = address;
        this.cottageOwner = cottageOwner;
        this.numberOfRatings = 0;
        this.rating = 0;
        this.coverImage = null;
        this.rooms = null;
        this.appointments = null;
        this.images = null;
        this.rules = null;
        this.deleted = false;
    }

}

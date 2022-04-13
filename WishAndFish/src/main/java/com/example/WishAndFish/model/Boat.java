package com.example.WishAndFish.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "boats")
public class Boat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "type", unique = false, nullable = false)
    private String type;

    @Column(name = "length", unique = false, nullable = false)
    private Double length;

    @Column(name = "engine_number", unique = false, nullable = false)
    private Integer engineNumber;

    @Column(name = "engine_power", unique = false, nullable = false)
    private String enginePower;

    @Column(name = "maxSpeed", unique = false, nullable = false)
    private Double maxSpeed;

    @OneToOne(targetEntity = Address.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    @Column(name = "capacity", unique = false, nullable = false)
    private Integer capacity;

    @Column(name = "average_grade", unique = false, nullable = true)
    private Double averageGrade;

    @OneToMany(mappedBy = "boat", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "boat", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany(mappedBy = "boat", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Rule> rules = new HashSet<>();

    @Column(name = "price_per_hour", unique = false)
    private Double pricePerHour;

    @OneToMany(mappedBy = "boat", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<NavigationEquipment> navigationEquipments = new HashSet<>();

    @OneToMany(mappedBy = "boat", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<FishingEquipment> fishingEquipments = new HashSet<>();

    @OneToMany(mappedBy = "boat", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<CancellationConditions> cancellationConditions = new HashSet<>();

    @Column(name="coverImage")
    private String coverImage;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "boat_owner_id")
    private BoatOwner boatOwner;

    @Column(name = "numberOfRatings")
    private int numberOfRatings;
    @Column(name="rating")
    private double rating;
    @Column(name = "deleted", unique = false, nullable = false)
    private boolean deleted;


    public Boat() {
        this.rating = 0;
        this.numberOfRatings = 0;
    }

    public Boat(long id, String name, String type, Double length, Integer engineNumber, String enginePower, Double maxSpeed, Address address, String description, Integer capacity, Double averageGrade, Set<Image> images, Set<Appointment> appointments, Set<Rule> rules, Double pricePerHour, Set<NavigationEquipment> navigationEquipments, Set<FishingEquipment> fishingEquipments, Set<CancellationConditions> cancellationConditions, String coverImage, BoatOwner boatOwner, int numberOfRatings, double rating, boolean deleted) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.length = length;
        this.engineNumber = engineNumber;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.address = address;
        this.description = description;
        this.capacity = capacity;
        this.averageGrade = averageGrade;
        this.images = images;
        this.appointments = appointments;
        this.rules = rules;
        this.pricePerHour = pricePerHour;
        this.navigationEquipments = navigationEquipments;
        this.fishingEquipments = fishingEquipments;
        this.cancellationConditions = cancellationConditions;
        this.coverImage = coverImage;
        this.boatOwner = boatOwner;
        this.numberOfRatings = numberOfRatings;
        this.rating = rating;
        this.deleted = deleted;
    }


    public Boat(String name, String type, Double length, Integer engineNumber, String enginePower, Double maxSpeed, Address address, String description, Integer capacity, Double pricePerHour) {
        this.name = name;
        this.type = type;
        this.length = length;
        this.engineNumber = engineNumber;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.address = address;
        this.description = description;
        this.capacity = capacity;
        this.averageGrade = 0.0;
        this.images = null;
        this.appointments = null;
        this.rules = null;
        this.pricePerHour = pricePerHour;
        this.navigationEquipments = null;
        this.fishingEquipments = null;
        this.cancellationConditions = null;
        this.coverImage = null;
        this.numberOfRatings = 0;
        this.rating = 0.0;
        this.deleted = false;
    }

}

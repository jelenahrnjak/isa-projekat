package com.example.WishAndFish.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "boat", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Image> images = new HashSet<Image>();

    @OneToMany(mappedBy = "boat", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<Appointment>();

    @OneToMany(mappedBy = "boat", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Rule> rules = new HashSet<Rule>();

    @Column(name = "price_per_hour", unique = false, nullable = false)
    private Double pricePerHour;

    @OneToMany(mappedBy = "boat", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<NavigationEquipment> navigationEquipments = new HashSet<NavigationEquipment>();

    @OneToMany(mappedBy = "boat", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<FishingEquipment> fishingEquipments = new HashSet<FishingEquipment>();

    @OneToMany(mappedBy = "boat", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<CancellationConditions> cancellationConditions = new HashSet<CancellationConditions>();


    public Boat() {
    }



}

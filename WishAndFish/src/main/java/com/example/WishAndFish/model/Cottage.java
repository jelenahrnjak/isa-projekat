package com.example.WishAndFish.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cottages")
public class Cottage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    @Column(name = "price_per_day", unique = false, nullable = false)
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
    @OneToMany(mappedBy = "cottage", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Room> rooms = new HashSet<Room>();

    @OneToMany(mappedBy = "cottage", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<Appointment>();

    @OneToMany(mappedBy = "cottage", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Image> images = new HashSet<Image>();

    @OneToMany(mappedBy = "cottage", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Rule> rules = new HashSet<Rule>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cottage_owner_id")
    private CottageOwner cottageOwner;

    public Cottage() {
    }

    public Cottage(long id, String name, String description, Double pricePerDay, Address address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.address = address;
    }

    public Cottage(String name, String description, Double pricePerDay, Address address, int numberOfRatings, double rating, String coverImage, Set<Room> rooms, Set<Appointment> appointments, Set<Image> images, Set<Rule> rules, CottageOwner cottageOwner) {
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
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public CottageOwner getCottageOwner() {
        return cottageOwner;
    }

    public void setCottageOwner(CottageOwner cottageOwner) {
        this.cottageOwner = cottageOwner;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}

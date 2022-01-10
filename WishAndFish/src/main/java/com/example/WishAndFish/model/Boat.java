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

    @Column(name = "price_per_hour", unique = false)
    private Double pricePerHour;

    @OneToMany(mappedBy = "boat", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<NavigationEquipment> navigationEquipments = new HashSet<NavigationEquipment>();

    @OneToMany(mappedBy = "boat", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<FishingEquipment> fishingEquipments = new HashSet<FishingEquipment>();

    @OneToMany(mappedBy = "boat", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<CancellationConditions> cancellationConditions = new HashSet<CancellationConditions>();

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



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Integer getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(Integer engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(String enginePower) {
        this.enginePower = enginePower;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Set<NavigationEquipment> getNavigationEquipments() {
        return navigationEquipments;
    }

    public void setNavigationEquipments(Set<NavigationEquipment> navigationEquipments) {
        this.navigationEquipments = navigationEquipments;
    }

    public Set<FishingEquipment> getFishingEquipments() {
        return fishingEquipments;
    }

    public void setFishingEquipments(Set<FishingEquipment> fishingEquipments) {
        this.fishingEquipments = fishingEquipments;
    }

    public Set<CancellationConditions> getCancellationConditions() {
        return cancellationConditions;
    }

    public void setCancellationConditions(Set<CancellationConditions> cancellationConditions) {
        this.cancellationConditions = cancellationConditions;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public BoatOwner getBoatOwner() {
        return boatOwner;
    }

    public void setBoatOwner(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


}

package com.example.WishAndFish.model;

import javax.persistence.*;

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

    @Column(name = "price_per_hour", unique = false, nullable = false)
    private Double pricePerHour;

    @Column(name = "description", unique = false, nullable = true)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fishing_instructor_id")
    private FishingInstructor fishingInstructor;

    @OneToOne(targetEntity = Address.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public FishingAdventure() {
    }

    public FishingAdventure(boolean deleted, String name, int numberOfRatings, double rating, String coverImage, Double pricePerHour, String declaration, FishingInstructor fishingInstructor, Address address) {
        this.deleted = deleted;
        this.name = name;
        this.numberOfRatings = numberOfRatings;
        this.rating = rating;
        this.coverImage = coverImage;
        this.pricePerHour = pricePerHour;
        this.description = declaration;
        this.fishingInstructor = fishingInstructor;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FishingInstructor getFishingInstructor() {
        return fishingInstructor;
    }

    public void setFishingInstructor(FishingInstructor fishingInstructor) {
        this.fishingInstructor = fishingInstructor;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

package com.example.WishAndFish.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "street", unique = false, nullable = false)
    private String street;
    @Column(name = "streetNumber", unique = false, nullable = false)
    private String streetNumber;
    @Column(name = "postalCode", unique = false, nullable = false)
    private String postalCode;
    @Column(unique = false, nullable = false)
    private String cityName;
    @Column(unique = false, nullable = false)
    private String countryName;
    @Column(name = "longitude", unique = false, nullable = true)
    private double longitude;
    @Column(name = "latitude", unique = false, nullable = true)
    private double latitude;
    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<User>();

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Cottage> cottages = new HashSet<Cottage>();

    public Address() {
    }

    public Address(String street, String streetNumber, String postalCode, String cityName, String countryName, double longitude, double latitude) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.cityName = cityName;
        this.countryName = countryName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Address(String street, String streetNumber, String postalCode, String cityName, String countryName) {

        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.cityName = cityName;
        this.countryName = countryName;
    }


    public Set<Cottage> getCottages() {
        return cottages;
    }

    public void setCottages(Set<Cottage> cottages) {
        this.cottages = cottages;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

package com.example.WishAndFish.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
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
    @Column(name = "longitude", unique = false, nullable = true)
    private String longitude;
    @Column(name = "latitude", unique = false, nullable = true)
    private String latitude;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cityId")
    private City city;
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<User>();

    public Address() {
    }

    public Address(String street, String streetNumber, String postalCode, String longitude, String latitude) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Address(String street, String streetNumber, String postalCode, String longitude, String latitude, City city, Set<User> users) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.users = users;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

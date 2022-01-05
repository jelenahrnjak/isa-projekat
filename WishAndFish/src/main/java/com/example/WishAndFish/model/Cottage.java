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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "cottage", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Room> rooms = new HashSet<Room>();

    @OneToMany(mappedBy = "cottage", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<Appointment>();

    @OneToMany(mappedBy = "cottage", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Image> images = new HashSet<Image>();

    @OneToMany(mappedBy = "cottage", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Rule> rules = new HashSet<Rule>();

    public Cottage() {
    }

    public Cottage(long id, String name, String description, Double pricePerDay, Address address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.address = address;
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
}

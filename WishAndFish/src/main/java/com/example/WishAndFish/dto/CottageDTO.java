package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class CottageDTO {

    private String name;
    private String description;
    private Double pricePerDay;
    private Address address;
    private Set<Room> rooms = new HashSet<Room>();
    private Set<Appointment> appointments = new HashSet<Appointment>();
    private Set<Image> images = new HashSet<Image>();
    private Set<Rule> rules = new HashSet<Rule>();

    public CottageDTO() {
    }

    public CottageDTO(String name, String description, Double pricePerDay, Address address, Set<Room> rooms, Set<Appointment> appointments, Set<Image> images, Set<Rule> rules) {
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.address = address;
        this.rooms = rooms;
        this.appointments = appointments;
        this.images = images;
        this.rules = rules;
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
}

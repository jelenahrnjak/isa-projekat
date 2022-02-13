package com.example.WishAndFish.model;

import com.example.WishAndFish.dto.RoomDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.servlet.http.Cookie;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "bed_number", unique = false, nullable = false)
    private Integer bedNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cottage_id", nullable = false)
    @JsonBackReference
    private Cottage cottage;

    public Room() {
    }

    public Room(Integer bedNumber, Cottage cottage) {
        this.bedNumber = bedNumber;
        this.cottage = cottage;
    }

    public Room(RoomDTO roomDTO){
        this.bedNumber = roomDTO.getBedNumber();
    }

    public Integer getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(Integer bedNumber) {
        this.bedNumber = bedNumber;
    }

    public Cottage getCottage() {
        return cottage;
    }

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
    }
}

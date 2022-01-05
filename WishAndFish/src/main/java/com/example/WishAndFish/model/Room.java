package com.example.WishAndFish.model;

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
    private Cottage cottage;
}

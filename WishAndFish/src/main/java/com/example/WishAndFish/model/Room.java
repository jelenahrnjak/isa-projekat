package com.example.WishAndFish.model;

import com.example.WishAndFish.dto.RoomDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.servlet.http.Cookie;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    public Room(Integer bedNumber, Cottage cottage) {
        this.bedNumber = bedNumber;
        this.cottage = cottage;
    }


}

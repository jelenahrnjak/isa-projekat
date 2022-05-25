package com.example.WishAndFish.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "additional_services")
public class AdditionalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "price", unique = false, nullable = false)
    private String price;

    @Column(name = "deleted", unique = false, nullable = false)
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cottage_id", nullable = true)
    @JsonBackReference
    private Cottage cottage;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "boat_id", nullable = true)
    @JsonBackReference
    private Boat boat;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "adventure_id", nullable = true)
    @JsonBackReference
    private FishingAdventure fishingAdventure;

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//    @JoinColumn(name = "appointment_id", nullable = true)
//    @JsonBackReference
//    private Appointment appointment;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "appointment_additional_services",
            joinColumns = @JoinColumn(name = "additional_service_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "id"))
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<>();

}

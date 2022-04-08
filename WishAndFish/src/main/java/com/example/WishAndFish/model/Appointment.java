package com.example.WishAndFish.model;

import com.example.WishAndFish.dto.AvailabilityDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_date", unique = false, nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime startDate;

    @Column(name = "end_date", unique = false, nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime endDate;

    @Column(name = "max_persons", unique = false, nullable = true)
    private Integer maxPersons;

    @Column(name = "price", unique = false, nullable = true)
    private Double price;

    @Column(name = "duration", unique = false, nullable = false)
    private Duration duration;

    @Column(name = "reserved", unique = false, nullable = false)
    private Boolean reserved;

    @Column(name = "deleted", unique = false, nullable = false)
    private boolean deleted;

    @Column(name = "action", unique = false, nullable = true) //ispraviti na false
    private Boolean isAction;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cottage_id", nullable = true)
    @JsonBackReference
    private Cottage cottage;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "boat_id", nullable = true)
    @JsonBackReference
    private Boat boat;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fishing_adventure_id", nullable = true)
    @JsonBackReference
    private FishingAdventure fishingAdventure;

    @OneToMany(mappedBy = "appointment", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<AdditionalService> additionalServices = new HashSet<AdditionalService>();

    //@ManyToMany(mappedBy = "appointments")
    //private Set<AdditionalService> additionalServices = new HashSet<AdditionalService>();

    @Column(name = "action", unique = false, nullable = false)
    private boolean action;

    public Cottage getCottage() {
        return cottage;
    }

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
    }

}

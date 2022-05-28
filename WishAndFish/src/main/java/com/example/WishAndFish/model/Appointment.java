package com.example.WishAndFish.model;

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
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
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

    @Column(name = "expiration_date", unique = false, nullable = true)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime expirationDate;

    @Column(name = "max_persons", unique = false, nullable = true)
    private Integer maxPersons;

    @Column(name = "price", unique = false, nullable = true)
    private Double price;

    @Column(name = "duration", unique = false, nullable = true) //obrisati
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "boat_id", nullable = true)
    @JsonBackReference
    private Boat boat;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fishing_adventure_id", nullable = true)
    @JsonBackReference
    private FishingAdventure fishingAdventure;

//    @OneToMany(mappedBy = "appointment", fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
//    @JsonManagedReference
//    private Set<AdditionalService> additionalServices = new HashSet<>();


    //@ManyToMany(mappedBy = "appointments")
    @ManyToMany(mappedBy = "appointments", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<AdditionalService> additionalServices = new HashSet<AdditionalService>();


    public Cottage getCottage() {
        return cottage;
    }

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
    }

    public Appointment(){
        this.setDeleted(false);
        this.setReserved(false);
    }

    public Appointment(Appointment a){
        this.startDate = a.getStartDate();
        this.endDate = a.getEndDate();
        this.expirationDate = a.getExpirationDate();
        this.maxPersons = a.getMaxPersons();
        this.price = a.getPrice();
        this.duration = a.getDuration();
        this.reserved = a.getReserved();
        this.deleted = a.isDeleted();
        this.isAction = a.getIsAction();
        this.cottage = a.getCottage();
        this.boat = a.getBoat();
        this.fishingAdventure = a.getFishingAdventure();
        this.additionalServices = a.getAdditionalServices();

    }
}

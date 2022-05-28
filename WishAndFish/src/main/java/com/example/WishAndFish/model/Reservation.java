package com.example.WishAndFish.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Client.class, cascade = CascadeType.MERGE)
    private Client client;

    @OneToOne(targetEntity = Appointment.class, cascade = CascadeType.MERGE)
    private Appointment appointment;

    @Column(name = "canceled", nullable = false)
    private Boolean canceled;

    @Column(name = "finished", nullable = true)
    private Boolean finished;

    @Column(name = "commented")
    private Boolean commented;

    @Column(name = "total_price", unique = false, nullable = false)
    private Double totalPrice;

    @Column(name = "commented_owner")
    private Boolean commentedOwner;

    @Column(name = "commented_entity")
    private Boolean commentedEntity;


    public Reservation(Client client, Appointment appointment){

        this.client = client;
        this.appointment = appointment;
        this.canceled = false;
        this.finished = false;
        this.commented = false;
        this.commentedEntity = false;
        this.commentedOwner = false;
        this.totalPrice = appointment.getPrice();

    }


}

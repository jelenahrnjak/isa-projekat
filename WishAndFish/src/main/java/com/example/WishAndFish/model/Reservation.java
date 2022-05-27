package com.example.WishAndFish.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    public Reservation(Client client, Appointment appointment){

        this.client = client;
        this.appointment = appointment;
        this.canceled = false;
        this.finished = false;
        this.commented = false;
        this.totalPrice = appointment.getPrice();

    }

}

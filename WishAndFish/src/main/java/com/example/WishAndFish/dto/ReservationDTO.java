package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.Appointment;
import com.example.WishAndFish.model.Client;
import com.example.WishAndFish.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReservationDTO {

    private Long id;
    private Client client;
    private Appointment appointment;
    private Boolean canceled;
    private Boolean finished;


    public ReservationDTO(Reservation r){
        this.id = r.getId();
        this.client = r.getClient();
        this.appointment = r.getAppointment();
        this.canceled = r.getCanceled();
        this.finished = r.getFinished();
    }
}

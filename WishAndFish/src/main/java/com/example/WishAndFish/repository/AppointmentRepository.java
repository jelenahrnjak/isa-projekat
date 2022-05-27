package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT u FROM Appointment u WHERE cottage_id is not null")
    Collection<Appointment> findCottage();

}

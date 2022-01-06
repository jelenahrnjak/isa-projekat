package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}

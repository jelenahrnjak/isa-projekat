package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}

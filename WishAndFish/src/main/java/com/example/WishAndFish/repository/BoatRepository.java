package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatRepository extends JpaRepository<Boat, Long> {
}

package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CottageRepository extends JpaRepository<Cottage, Long> {
}

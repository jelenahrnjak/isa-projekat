package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.WishAndFish.dto.CottageDTO;

import java.util.List;

public interface CottageRepository extends JpaRepository<Cottage, Long> {
    List<Cottage> findAll();
}

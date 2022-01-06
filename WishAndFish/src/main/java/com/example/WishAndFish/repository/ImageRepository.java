package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}

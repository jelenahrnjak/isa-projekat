package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

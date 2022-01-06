package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.LoyaltyCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyCategoryRepository extends JpaRepository<LoyaltyCategory, Long> {
    LoyaltyCategory findByLevel(int level);
}

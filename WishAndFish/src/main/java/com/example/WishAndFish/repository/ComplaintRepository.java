package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}

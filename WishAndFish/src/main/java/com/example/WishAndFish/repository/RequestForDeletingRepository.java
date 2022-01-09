package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.RequestForDeleting;
import com.example.WishAndFish.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestForDeletingRepository extends JpaRepository<RequestForDeleting, Long> {

    RequestForDeleting findByUser(User u);
}

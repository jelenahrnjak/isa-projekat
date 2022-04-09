package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String userEmail);
}

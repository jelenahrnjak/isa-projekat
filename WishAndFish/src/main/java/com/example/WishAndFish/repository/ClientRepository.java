package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.Client;
import com.example.WishAndFish.model.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String userEmail);

    @Query(value = "SELECT s.cottage_subscriptions_id FROM client_cottage_subscriptions s  WHERE s.client_id = :id", nativeQuery = true)
    Set<Long> getCottageSubscriptions(Long id);
}

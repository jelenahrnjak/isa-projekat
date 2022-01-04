package com.example.WishAndFish.repository;

import com.example.WishAndFish.security.auth.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
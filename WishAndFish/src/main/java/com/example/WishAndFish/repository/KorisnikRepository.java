package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorisnikRepository extends JpaRepository<User, Integer> {

}

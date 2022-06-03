package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    User findByVerificationCode(String code);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select u from User u")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    List<User> findAllPessimistic();

}

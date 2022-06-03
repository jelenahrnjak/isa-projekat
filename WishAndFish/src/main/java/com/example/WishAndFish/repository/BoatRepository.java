package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

public interface BoatRepository extends JpaRepository<Boat, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select b from Boat b where b.id = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public Boat findOneById(@Param("id")Long id);
}

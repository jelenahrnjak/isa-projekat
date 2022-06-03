package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

public interface CottageRepository extends JpaRepository<Cottage, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Cottage c where c.id = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public Cottage findOneById(@Param("id")Long id);
}

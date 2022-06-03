package com.example.WishAndFish.repository;
import com.example.WishAndFish.model.FishingAdventure;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

public interface FishingAdventureRepository  extends JpaRepository<FishingAdventure, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from FishingAdventure a where a.id = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public FishingAdventure findOneById(@Param("id")Long id);

}

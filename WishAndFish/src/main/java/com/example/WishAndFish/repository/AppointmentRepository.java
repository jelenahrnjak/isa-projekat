package com.example.WishAndFish.repository;

import com.example.WishAndFish.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Appointment a where a.id = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public Appointment findOneById(@Param("id")Long id);

}

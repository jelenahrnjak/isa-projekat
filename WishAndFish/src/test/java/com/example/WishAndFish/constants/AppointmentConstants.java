package com.example.WishAndFish.constants;

import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.model.FishingAdventure;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.type.LocalDateTimeType;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentConstants {

    public static final Long DB_ID = 1L;
    public static final LocalDateTime DB_START_DATE = LocalDateTime.now().minusDays(10);
    public static final LocalDateTime DB_END_DATE = LocalDateTime.now().minusDays(5);
    public static final LocalDateTime DB_EXPIRATION_DATE = LocalDateTime.now().minusDays(14);
    public static final Integer DB_MAX_PERSONS = 5;
    public static final Double DB_PRICE = 750.0;
    public static final Boolean DB_RESERVED = false;
    public static final Boolean DB_DELETED = false;
    public static final Boolean DB_IS_ACTION = false;

    public static final Long DB_ID_TO_DELETE = 2l;
    public static final Long DB_NEW_ID = 3l;
}

package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.Cottage;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AppointmentSearchDTO {
 
    private String name;
    private String address;
    private String rating;
    private String price;
    private String startDate;
    private String endDate;
    private Integer maxPersons;
}

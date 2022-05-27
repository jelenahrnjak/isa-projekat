package com.example.WishAndFish.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateReservationDTO {

    private String user;
    private String start;
    private String end;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double totalPrice;
    private Boolean isAction;
    private ArrayList<AdditionalServicesDTO> additionalServices = new ArrayList<>();
    public Long entity;
    public Long entityId;
}

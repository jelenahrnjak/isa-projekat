package com.example.WishAndFish.dto;
import com.example.WishAndFish.model.Appointment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime endDate;
    private Integer maxPersons;
    private Double price;
    private Duration duration;
    private Boolean reserved;
    private Boolean isAction;

    public AppointmentDTO(Appointment a){
        this.id = a.getId();
        this.startDate = a.getStartDate();
        this.endDate = a.getEndDate();
        this.maxPersons = a.getMaxPersons();
        this.price = a.getPrice();
        this.duration = a.getDuration();
        this.reserved = a.getReserved();
        this.isAction = a.getIsAction();
    }
}
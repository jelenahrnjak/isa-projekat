package com.example.WishAndFish.dto;
import com.example.WishAndFish.model.Appointment;
import com.example.WishAndFish.model.Cottage;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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
    private CottageDTO cottage;
    private BoatDTO boat;
    private FishingAdventureDTO adventure;

    public AppointmentDTO(Appointment a){
        this.id = a.getId();
        this.startDate = a.getStartDate();
        this.endDate = a.getEndDate();
        this.maxPersons = a.getMaxPersons();
        this.price = a.getPrice();
        this.duration = a.getDuration();
        this.reserved = a.getReserved();
        this.isAction = a.getIsAction();

        if(a.getCottage() != null){
            this.cottage = new CottageDTO(a.getCottage());
        }else if(a.getBoat() != null){
            this.boat = new BoatDTO(a.getBoat());
        }else if(a.getFishingAdventure() != null){
            this.adventure = new FishingAdventureDTO(a.getFishingAdventure());
        }

    }
    
    public AppointmentDTO(String startDate, String endDate, Integer maxPersons){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        this.startDate = start.atStartOfDay();
        this.endDate = end.atStartOfDay();
        this.maxPersons = maxPersons;
    }

    public AppointmentDTO(String startDate, String startTime, Integer hours, Integer maxPersons){


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate);
        LocalTime time = LocalTime.parse(startTime);

        this.startDate = start.atTime(time);
        this.maxPersons = maxPersons;
        this.endDate = start.atTime(time).plusHours(hours);
    }

}
package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.AdditionalService;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddActionDTO {

    private Long id;
    private String startDate;
    private String endDate;
    private String expirationDate;
    private Integer maxPersons;
    private Double price;
    private List<Long> additionalServices;
}

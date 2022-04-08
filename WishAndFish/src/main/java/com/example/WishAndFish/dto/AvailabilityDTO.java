package com.example.WishAndFish.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityDTO {
    private Long id;
    private String startDate;
    private String endDate;

}

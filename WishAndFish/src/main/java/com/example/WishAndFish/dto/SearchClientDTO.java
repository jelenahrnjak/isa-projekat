package com.example.WishAndFish.dto;

import lombok.*;

import javax.persistence.SecondaryTable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SearchClientDTO {

    private String criteria;

}

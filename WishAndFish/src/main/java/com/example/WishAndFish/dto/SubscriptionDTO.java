package com.example.WishAndFish.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDTO {

    public String userEmail;
    public Long cottageId;
    public Long boatId;
    public Long adventureId;
}

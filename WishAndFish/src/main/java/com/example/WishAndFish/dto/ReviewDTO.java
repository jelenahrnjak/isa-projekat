package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.Client;
import com.example.WishAndFish.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReviewDTO {

    private String content;
    private Integer rating;
    private Client client;
    private LocalDateTime date;

    public ReviewDTO(Review r){
        this.content = r.getContent();
        this.rating = r.getRating();
        this.client = r.getClient();
        this.date = r.getDate();
    }
}

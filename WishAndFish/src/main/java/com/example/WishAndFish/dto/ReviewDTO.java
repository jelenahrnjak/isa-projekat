package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.Client;
import com.example.WishAndFish.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReviewDTO {

    private String content;
    private Integer rating;
    private Client client;

    public ReviewDTO(Review r){
        this.content = r.getContent();
        this.rating = r.getRating();
        this.client = r.getClient();
    }
}

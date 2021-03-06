package com.example.WishAndFish.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CommentDTO {
    private String content;
    private Boolean bad;
    private Boolean came;
    private String client;
    private Long reservationID;
    private Integer rate;
    private Boolean isOwner;
    private Boolean approved;

    public CommentDTO(String content, String client, Long reservationID, Integer rate, Boolean isOwner){
        this.content = content;
        this.client = client;
        this.reservationID = reservationID;
        this.rate = rate;
        this.isOwner = isOwner;
    }

}

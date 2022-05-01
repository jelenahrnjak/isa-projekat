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
}

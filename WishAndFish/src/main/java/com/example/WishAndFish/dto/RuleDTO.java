package com.example.WishAndFish.dto;

import com.example.WishAndFish.model.Rule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RuleDTO {
    private Long id;
    private String content;

    public RuleDTO(Rule r){
        this.id = r.getId();
        this.content = r.getContent();
    }
}

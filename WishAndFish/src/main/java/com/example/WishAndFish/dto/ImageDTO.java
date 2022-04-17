package com.example.WishAndFish.dto;


import com.example.WishAndFish.model.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    public String path;

    public ImageDTO(Image i){
        this.path = i.getPath();
    }

}

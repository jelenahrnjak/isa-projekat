package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.AddCottageImageDTO;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.model.Image;
import com.example.WishAndFish.repository.CottageRepository;
import com.example.WishAndFish.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CottageRepository cottageRepository;

    public String addImage(AddCottageImageDTO dto){
        for(Cottage c: this.cottageRepository.findAll()){
            if(dto.getCottageId().equals(c.getId())){
                Image image = new Image(dto.getPath());
                image.setCottage(c);
                this.imageRepository.save(image);
            }
        }
        return dto.getPath();
    }
}

package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.AddCottageImageDTO;
import com.example.WishAndFish.dto.ImageDTO;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.model.Image;
import com.example.WishAndFish.repository.CottageRepository;
import com.example.WishAndFish.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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


    public ResponseEntity<Long> deleteImage(String path){
        System.out.println(path);
        for(Image i: imageRepository.findAll()){
            if(i.getPath().equals(path)){
                i.setDeleted(true);
                this.imageRepository.save(i);
                return new ResponseEntity<Long>(i.getId(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<Long>(-1L, HttpStatus.NOT_FOUND);
    }


    public List<ImageDTO> getAllByCottage(Long id) {

        List<ImageDTO> ret = new ArrayList<ImageDTO>();
        for(Image i : imageRepository.findAll()){
            if(id.equals(i.getCottage().getId()) && !i.isDeleted()){
                ret.add(new ImageDTO(i));
            }
        };
        return ret;
    }
}

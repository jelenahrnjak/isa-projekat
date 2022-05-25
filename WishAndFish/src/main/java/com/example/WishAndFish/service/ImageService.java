package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.AddCottageImageDTO;
import com.example.WishAndFish.dto.ImageDTO;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.model.Image;
import com.example.WishAndFish.repository.BoatRepository;
import com.example.WishAndFish.repository.CottageRepository;
import com.example.WishAndFish.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CottageRepository cottageRepository;

    @Autowired
    private BoatRepository boatRepository;

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

    public String addImageBoat(AddCottageImageDTO dto){
        for(Boat b: this.boatRepository.findAll()){
            if(dto.getCottageId().equals(b.getId())){
                Image image = new Image(dto.getPath());
                image.setBoat(b);
                this.imageRepository.save(image);
            }
        }
        return dto.getPath();
    }

    public String addCoverImageBoat(AddCottageImageDTO dto){
        for(Boat b: this.boatRepository.findAll()){
            if(dto.getCottageId().equals(b.getId())){
                Image image = new Image(dto.getPath());
                image.setBoat(b);
                b.setCoverImage(dto.getPath());
                this.boatRepository.save(b);
                this.imageRepository.save(image);
            }
        }
        return dto.getPath();
    }

    public String addCoverImageCottage(AddCottageImageDTO dto){
        for(Cottage b: this.cottageRepository.findAll()){
            if(dto.getCottageId().equals(b.getId())){
                Image image = new Image(dto.getPath());
                image.setCottage(b);
                b.setCoverImage(dto.getPath());
                this.cottageRepository.save(b);
                this.imageRepository.save(image);
            }
        }
        return dto.getPath();
    }
    public ResponseEntity<Long> deleteImage(String path){
        System.out.println(path);
        for(Cottage c: cottageRepository.findAll()){
            if(c.getCoverImage().equals(path)){
                c.setCoverImage("");
                cottageRepository.save(c);
            }
        }

        for(Boat b: boatRepository.findAll()){
            if(b.getCoverImage().equals(path)){
                b.setCoverImage("");
                boatRepository.save(b);
            }
        }

        for(Image i: imageRepository.findAll()){
            if(i.getPath().equals(path)){
                i.setDeleted(true);
                this.imageRepository.save(i);
                return new ResponseEntity<>(i.getId(), HttpStatus.OK);
            }
        }



        return new ResponseEntity<>(-1L, HttpStatus.NOT_FOUND);
    }


    public List<ImageDTO> getAllByCottage(Long id) {

        List<ImageDTO> ret = new ArrayList<>();
        for(Image i : imageRepository.findAll()){
            if(i.getCottage() != null && id.equals(i.getCottage().getId()) && !i.isDeleted()){
                ret.add(new ImageDTO(i));
            }
        };
        return ret;
    }


    public List<ImageDTO> getAllByBoat(Long id) {

        List<ImageDTO> ret = new ArrayList<>();
        for(Image i : imageRepository.findAll()){
            if(i.getBoat() != null && id.equals(i.getBoat().getId()) && !i.isDeleted()){
                ret.add(new ImageDTO(i));
            }
        };
        return ret;
    }
}

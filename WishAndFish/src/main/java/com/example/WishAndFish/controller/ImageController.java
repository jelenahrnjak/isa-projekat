package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.AddCottageImageDTO;
import com.example.WishAndFish.dto.ImageDTO;
import com.example.WishAndFish.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/images")
@CrossOrigin()
public class ImageController {

    @Autowired
    private ImageService imageService;
    private final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @RequestMapping(value="/getAllByCottage/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public List<ImageDTO> getAllByCottage(@PathVariable Long id) {
        return this.imageService.getAllByCottage(id);
    }

    @RequestMapping(value="/addImage", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public String addImage(@RequestBody AddCottageImageDTO dto) {
        return this.imageService.addImage(dto);
    }

    @RequestMapping(value="/addImageBoat", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<String> addImageBoat(@RequestBody AddCottageImageDTO dto) {
        String path =  this.imageService.addImageBoat(dto);
        if(path != null){
            return new ResponseEntity<>(path, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/addCoverImageBoat", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<String> addCoverImageBoat(@RequestBody AddCottageImageDTO dto) {
        String path =  this.imageService.addCoverImageBoat(dto);
        if(path != null){
            return new ResponseEntity<>(path, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/addCoverImageCottage", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public ResponseEntity<String> addCoverImageCottage(@RequestBody AddCottageImageDTO dto) {
        String path =  this.imageService.addCoverImageCottage(dto);
        if(path != null){
            return new ResponseEntity<>(path, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value="/deleteImage/{path}")
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER') || hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<Long> deleteImage(@PathVariable String path) {
        return this.imageService.deleteImage(path);
    }

    @RequestMapping(value="/getAllByBoat/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public List<ImageDTO> getAllByBoat(@PathVariable Long id) {
        return this.imageService.getAllByBoat(id);
    }
}

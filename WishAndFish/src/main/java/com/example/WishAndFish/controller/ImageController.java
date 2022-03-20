package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.AddCottageImageDTO;
import com.example.WishAndFish.dto.EditCottageDTO;
import com.example.WishAndFish.model.Image;
import com.example.WishAndFish.service.CottageService;
import com.example.WishAndFish.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/images")
@CrossOrigin()
public class ImageController {

    @Autowired
    private ImageService imageService;
    private Logger logger = LoggerFactory.getLogger(ImageController.class);

    @RequestMapping(value="/addImage", method = RequestMethod.POST)
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public String addImage(@RequestBody AddCottageImageDTO dto) {
        return this.imageService.addImage(dto);
    }
}

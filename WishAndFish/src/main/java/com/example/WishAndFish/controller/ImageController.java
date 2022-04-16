package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.AddCottageImageDTO;
import com.example.WishAndFish.dto.ImageDTO;
import com.example.WishAndFish.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public List<ImageDTO> getAllByCottage(@PathVariable Long id) {
        return this.imageService.getAllByCottage(id);
    }

    @RequestMapping(value="/addImage", method = RequestMethod.POST)
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public String addImage(@RequestBody AddCottageImageDTO dto) {
        return this.imageService.addImage(dto);
    }

    @DeleteMapping(value="/deleteImage/{path}")
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<Long> deleteImage(@PathVariable String path) {
        return this.imageService.deleteImage(path);
    }

    @RequestMapping(value="/getAllByBoat/{id}", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('BOAT_OWNER')")
    public List<ImageDTO> getAllByBoat(@PathVariable Long id) {
        return this.imageService.getAllByBoat(id);
    }
}

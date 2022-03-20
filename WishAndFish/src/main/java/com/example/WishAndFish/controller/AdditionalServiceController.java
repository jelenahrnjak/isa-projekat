package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.AdditionalServicesDTO;
import com.example.WishAndFish.dto.CottageDTO;
import com.example.WishAndFish.service.AdditionalServiceService;
import com.example.WishAndFish.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/additionalServices")
@CrossOrigin()
public class AdditionalServiceController {

    @Autowired
    private AdditionalServiceService additionalServiceService;

    @RequestMapping(value="/getAllByCottage/{id}", method = RequestMethod.GET)
    public List<AdditionalServicesDTO> getAllByCottage(@PathVariable Long id) {
        return this.additionalServiceService.getAllByCottage(id);
    }
}

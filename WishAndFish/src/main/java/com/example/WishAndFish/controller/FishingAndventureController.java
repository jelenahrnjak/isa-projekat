package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.FishingAdventureDTO;
import com.example.WishAndFish.service.FishingAdventuresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/adventures")
@CrossOrigin()
public class FishingAndventureController {

    @Autowired
    private FishingAdventuresService fishingAdventuresService;

    @RequestMapping(value="", method = RequestMethod.GET)
    public List<FishingAdventureDTO> getAll() {
        return this.fishingAdventuresService.findAll();
    }

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public List<FishingAdventureDTO> search(FishingAdventureDTO dto) {
        return this.fishingAdventuresService.search(dto);
    }
}

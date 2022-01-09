package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.BoatDTO;
import com.example.WishAndFish.dto.CottageDTO;
import com.example.WishAndFish.service.BoatOwnerService;
import com.example.WishAndFish.service.CottageOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/boatOwner")
@CrossOrigin()
public class BoatOwnerController {
    @Autowired
    private BoatOwnerService boatOwnerService;

    @RequestMapping(value="/getBoatsFromOwner/{email}", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public List<BoatDTO> getCottagesFromOwner(@PathVariable String email) {
        return this.boatOwnerService.getBoatsFromOwner(email);
    }
}

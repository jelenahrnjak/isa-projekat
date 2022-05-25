package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.BoadDisplayDTO;
import com.example.WishAndFish.service.BoatOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/boatOwner")
@CrossOrigin()
public class BoatOwnerController {
    @Autowired
    private BoatOwnerService boatOwnerService;

    @RequestMapping(value="/getBoatsFromOwner/{email}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public List<BoadDisplayDTO> getCottagesFromOwner(@PathVariable String email) {
        return this.boatOwnerService.getBoatsFromOwner(email);
    }
}

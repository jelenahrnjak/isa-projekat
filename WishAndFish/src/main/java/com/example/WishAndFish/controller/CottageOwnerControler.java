package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.CottageDisplayDTO;
import com.example.WishAndFish.service.CottageOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/cottageOwner")
@CrossOrigin()
public class CottageOwnerControler {

    @Autowired
    private CottageOwnerService cottageOwnerService;

    @RequestMapping(value="/getCottagesFromOwner/{email}", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public List<CottageDisplayDTO> getCottagesFromOwner(@PathVariable String email) {
        return this.cottageOwnerService.getCottagesFromOwner(email);
    }

}

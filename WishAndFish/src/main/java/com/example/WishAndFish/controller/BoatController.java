package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.AddBoatDTO;
import com.example.WishAndFish.dto.AddCottageDTO;
import com.example.WishAndFish.dto.BoatDTO;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/boats")
@CrossOrigin()
public class BoatController {

    @Autowired
    private BoatService boatService;

    @RequestMapping(value="", method = RequestMethod.GET)
    public List<BoatDTO> getAll() {
        return this.boatService.findAll();
    }

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public List<BoatDTO> search(BoatDTO dto) {
        return this.boatService.search(dto);
    }

    @PostMapping(value="/addBoat")
    //@PreAuthorize("hasRole('BOAT_OWNER')")
    public AddBoatDTO addBoat(@RequestBody AddBoatDTO newBoat) {
        return this.boatService.addBoat(newBoat);
    }

}

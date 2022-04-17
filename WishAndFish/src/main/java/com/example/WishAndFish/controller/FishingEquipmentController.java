package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.FishingEquipmentDTO;
import com.example.WishAndFish.dto.NavigationEquipmentDTO;
import com.example.WishAndFish.service.FishingEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "api/fishingEquipments")
@CrossOrigin()
public class FishingEquipmentController {

    @Autowired
    FishingEquipmentService fishingEquipmentService;

    @RequestMapping(value="/getAllByBoat/{id}", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('BOAT_OWNER')")
    public List<FishingEquipmentDTO> getAllByBoat(@PathVariable Long id) {
        return this.fishingEquipmentService.getAllByBoat(id);
    }
}

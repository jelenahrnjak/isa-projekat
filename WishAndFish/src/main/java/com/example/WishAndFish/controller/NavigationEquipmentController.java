package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.NavigationEquipmentDTO;
import com.example.WishAndFish.dto.RuleDTO;
import com.example.WishAndFish.service.NavigationEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/navigationEquipments")
@CrossOrigin()
public class NavigationEquipmentController {

    @Autowired
    NavigationEquipmentService navigationEquipmentService;

    @RequestMapping(value="/getAllByBoat/{id}", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('BOAT_OWNER')")
    public List<NavigationEquipmentDTO> getAllByBoat(@PathVariable Long id) {
        return this.navigationEquipmentService.getAllByBoat(id);
    }
}

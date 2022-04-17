package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.NavigationEquipmentDTO;
import com.example.WishAndFish.dto.RuleDTO;
import com.example.WishAndFish.model.NavigationEquipment;
import com.example.WishAndFish.model.Rule;
import com.example.WishAndFish.service.NavigationEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value="/addNavigationEquipment", method = RequestMethod.POST)
    //@PreAuthorize("hasRole('BOAT_OWNER')")
    public ResponseEntity<NavigationEquipment> addNavigationEquipment(@RequestBody NavigationEquipmentDTO dto) {
        NavigationEquipment ne = this.navigationEquipmentService.addNavigationEquipment(dto);
        if(ne!=null){
            return new ResponseEntity<>(ne, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}

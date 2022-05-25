package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.FishingEquipmentDTO;
import com.example.WishAndFish.dto.NavigationEquipmentDTO;
import com.example.WishAndFish.model.FishingEquipment;
import com.example.WishAndFish.model.NavigationEquipment;
import com.example.WishAndFish.service.FishingEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "api/fishingEquipments")
@CrossOrigin()
public class FishingEquipmentController {

    @Autowired
    FishingEquipmentService fishingEquipmentService;

    @RequestMapping(value="/getAllByBoat/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public List<FishingEquipmentDTO> getAllByBoat(@PathVariable Long id) {
        return this.fishingEquipmentService.getAllByBoat(id);
    }

    @RequestMapping(value="/addFishingEquipment", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<FishingEquipment> addFishingEquipment(@RequestBody FishingEquipmentDTO dto) {
        FishingEquipment fe = this.fishingEquipmentService.addFishingEquipment(dto);
        if(fe!=null){
            return new ResponseEntity<>(fe, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping(value="/deleteFishingEquipment/{id}")
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<Long> deleteFishingEquipment(@PathVariable Long id) {
        return this.fishingEquipmentService.deleteFishingEquipment(id);
    }

}

package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.AdditionalServicesDTO;
import com.example.WishAndFish.model.AdditionalService;
import com.example.WishAndFish.service.AdditionalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/additionalServices")
@CrossOrigin()
public class AdditionalServiceController {

    @Autowired
    private AdditionalServiceService additionalServiceService;

    @RequestMapping(value="/getAllByCottage/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public List<AdditionalServicesDTO> getAllByCottage(@PathVariable Long id) {
        return this.additionalServiceService.getAllByCottage(id);
    }

    @RequestMapping(value="/getAllByBoat/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public List<AdditionalServicesDTO> getAllByBoat(@PathVariable Long id) {
        return this.additionalServiceService.getAllByBoat(id);
    }

    @RequestMapping(value="/getAllByAdventure/{id}", method = RequestMethod.GET)
    public List<AdditionalServicesDTO> getAllByAdventure(@PathVariable Long id) {
        return this.additionalServiceService.getAllByAdventure(id);
    }

    @DeleteMapping(value="/deleteAdditionalServices/{id}")
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER') || hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<Long> deleteAdditionalServices(@PathVariable Long id) {
        return this.additionalServiceService.deleteAdditionalServices(id);
    }

    @RequestMapping(value="/addAdditionalService", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public ResponseEntity<AdditionalService> addAdditionalService(@RequestBody AdditionalServicesDTO dto) {
        AdditionalService as = this.additionalServiceService.addAdditionalService(dto);
        if(as != null){
            return new ResponseEntity<>(as, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/addAdditionalServiceBoat", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<AdditionalService> addAdditionalServiceBoat(@RequestBody AdditionalServicesDTO dto) {
        AdditionalService as =  this.additionalServiceService.addAdditionalServiceBoat(dto);
        if(as != null){
            return new ResponseEntity<>(as, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value="/findAdditionalServicesForAppointment/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER') || hasAuthority('ROLE_BOAT_OWNER')")
    public List<AdditionalServicesDTO> findAdditionalServicesForAppointment(@PathVariable Long id) {
        return this.additionalServiceService.findAdditionalServicesForAppointment(id);
    }


}

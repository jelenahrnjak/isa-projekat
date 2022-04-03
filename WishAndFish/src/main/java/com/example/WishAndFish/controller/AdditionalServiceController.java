package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.AddCottageImageDTO;
import com.example.WishAndFish.dto.AdditionalServicesDTO;
import com.example.WishAndFish.dto.CottageDTO;
import com.example.WishAndFish.model.AdditionalService;
import com.example.WishAndFish.service.AdditionalServiceService;
import com.example.WishAndFish.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/additionalServices")
@CrossOrigin()
public class AdditionalServiceController {

    @Autowired
    private AdditionalServiceService additionalServiceService;

    @RequestMapping(value="/getAllByCottage/{id}", method = RequestMethod.GET)
    public List<AdditionalServicesDTO> getAllByCottage(@PathVariable Long id) {
        return this.additionalServiceService.getAllByCottage(id);
    }

    @DeleteMapping(value="/deleteAdditionalServices/{id}")
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<Long> deleteAdditionalServices(@PathVariable Long id) {
        return this.additionalServiceService.deleteAdditionalServices(id);
    }

    @RequestMapping(value="/addAdditionalService", method = RequestMethod.POST)
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public AdditionalService addAdditionalService(@RequestBody AdditionalServicesDTO dto) {
        return this.additionalServiceService.addAdditionalService(dto);
    }


    @RequestMapping(value="/findAdditionalServicesForAppointment/{id}", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public List<AdditionalServicesDTO> findAdditionalServicesForAppointment(@PathVariable Long id) {
        return this.additionalServiceService.findAdditionalServicesForAppointment(id);
    }


}

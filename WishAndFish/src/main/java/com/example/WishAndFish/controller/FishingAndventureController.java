package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.AppointmentSearchDTO;
import com.example.WishAndFish.dto.CottageDTO;
import com.example.WishAndFish.dto.FishingAdventureDTO;
import com.example.WishAndFish.security.util.TokenUtils;
import com.example.WishAndFish.service.FishingAdventuresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/adventures")
@CrossOrigin()
public class FishingAndventureController {

    @Autowired
    private FishingAdventuresService fishingAdventuresService;

    @Autowired
    private TokenUtils tokenUtils;

    @RequestMapping(value="", method = RequestMethod.GET)
    public List<FishingAdventureDTO> getAll() {
        return this.fishingAdventuresService.findAll();
    }


    @RequestMapping(value="/client/{email}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public List<FishingAdventureDTO> getAllClients(@PathVariable String email) {
        return this.fishingAdventuresService.findAllClient(email);
    }


    @RequestMapping(value="/search", method = RequestMethod.GET)
    public List<FishingAdventureDTO> search(FishingAdventureDTO dto) {
        return this.fishingAdventuresService.mapSearch(dto);
    }

    @RequestMapping(value="/search/client/{email}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public List<FishingAdventureDTO> searchClient(@PathVariable String email, FishingAdventureDTO dto) {
        return this.fishingAdventuresService.searchClient(dto, email);
    }

    @RequestMapping(value = "/searchAppointments/{email}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public List<FishingAdventureDTO> searchAppointments(@PathVariable String email, AppointmentSearchDTO data){

        return this.fishingAdventuresService.searchAppointments(data, email);
    }
}

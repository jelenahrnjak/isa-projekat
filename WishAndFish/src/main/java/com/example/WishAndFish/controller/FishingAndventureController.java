package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.AppointmentSearchDTO;
import com.example.WishAndFish.dto.CottageDTO;
import com.example.WishAndFish.dto.FishingAdventureDTO;
import com.example.WishAndFish.security.util.TokenUtils;
import com.example.WishAndFish.service.FishingAdventuresService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<FishingAdventureDTO> getAllClients(@PathVariable String email) {
        return this.fishingAdventuresService.findAllClient(email);
    }


    @RequestMapping(value="/search", method = RequestMethod.GET)
    public List<FishingAdventureDTO> search(FishingAdventureDTO dto) {
        return this.fishingAdventuresService.mapSearch(dto);
    }

    @RequestMapping(value="/search/client/{email}", method = RequestMethod.GET)
    public List<FishingAdventureDTO> searchClient(@PathVariable String email, FishingAdventureDTO dto) {
        return this.fishingAdventuresService.searchClient(dto, email);
    }

    @RequestMapping(value = "/searchAppointments", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('CLIENT')")
    public List<FishingAdventureDTO> searchAppointments(AppointmentSearchDTO data){

        return this.fishingAdventuresService.searchAppointments(data);
    }
}

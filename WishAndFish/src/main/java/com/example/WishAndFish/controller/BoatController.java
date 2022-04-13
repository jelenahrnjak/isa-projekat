package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.AddBoatDTO;
import com.example.WishAndFish.dto.BoatDTO;
import com.example.WishAndFish.security.util.TokenUtils;
import com.example.WishAndFish.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/boats")
@CrossOrigin()
public class BoatController {

    @Autowired
    private BoatService boatService;

    @Autowired
    private TokenUtils tokenUtils;

    @RequestMapping(value="", method = RequestMethod.GET)
    public List<BoatDTO> getAll() {
        return this.boatService.findAll();
    }

    @RequestMapping(value="/client/{email}", method = RequestMethod.GET)
    public List<BoatDTO> getAllClients(@PathVariable String email) {
        return this.boatService.findAllClient(email);
    }

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public List<BoatDTO> search(BoatDTO dto) {

        return this.boatService.search(dto);
    }

    @RequestMapping(value="/search/client/{email}", method = RequestMethod.GET)
    public List<BoatDTO> searchClient(@PathVariable String email, BoatDTO dto) {
        return this.boatService.searchClient(dto,email);
    }

    @PostMapping(value="/addBoat")
    //@PreAuthorize("hasRole('BOAT_OWNER')")
    public AddBoatDTO addBoat(@RequestBody AddBoatDTO newBoat) {
        return this.boatService.addBoat(newBoat);
    }

    @DeleteMapping(value="/deleteBoat/{id}")
    //@PreAuthorize("hasRole('BOAT_OWNER')")
    public ResponseEntity<Long> deleteBoat(@PathVariable Long id) {
        return this.boatService.deleteBoat(id);
    }

}

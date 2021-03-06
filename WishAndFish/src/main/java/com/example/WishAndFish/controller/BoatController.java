package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.Cottage; 
import com.example.WishAndFish.security.util.TokenUtils;
import com.example.WishAndFish.service.BoatService;
import com.example.WishAndFish.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value="", method = RequestMethod.GET)
    public List<BoatDTO> getAll() {
        return this.boatService.findAll();
    }

    @RequestMapping(value="/client/{email}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public List<BoatDTO> getAllClients(@PathVariable String email) {
        return this.boatService.findAllClient(email);
    }

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public List<BoatDTO> search(BoatDTO dto) {

        return this.boatService.mapSearch(dto);
    }

    @RequestMapping(value="/search/client/{email}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public List<BoatDTO> searchClient(@PathVariable String email, BoatDTO dto) {
        return this.boatService.searchClient(dto,email);
    }

    @PostMapping(value="/addBoat")
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public AddBoatDTO addBoat(@RequestBody AddBoatDTO newBoat) {
        return this.boatService.addBoat(newBoat);
    }

    @DeleteMapping(value="/deleteBoat/{id}")
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<Long> deleteBoat(@PathVariable Long id) {

        Long b = this.boatService.deleteBoat(id);
        if(b == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(id,HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/findBoat/{id}", method = RequestMethod.GET)
//    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<BoatDetailDTO> findBoat(@PathVariable Long id) {
        BoatDetailDTO b = this.boatService.findBoat(id);

        if(b != null){
            return new ResponseEntity<>(b, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
 
    @RequestMapping(value = "/editBasicInfo", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<Boat> editBasicInfo(@RequestBody EditBoatDTO editedBoat) {
        Boat b = this.boatService.editBasicInfo(editedBoat);
        if(b != null){
            return new ResponseEntity<>(b, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  
    @RequestMapping(value = "/searchAppointments/{email}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public List<BoatDTO> searchAppointments(@PathVariable String email ,AppointmentSearchDTO data){
        return this.boatService.searchAppointments(data, email);
    }


    @RequestMapping(value = "/getAllCommentsBoat/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER') || hasAuthority('ROLE_CLIENT')")
    public List<ReviewDTO> getAllCommentsBoat(@PathVariable Long id){
        return this.reviewService.getAllCommentsBoat(id);
    }

}

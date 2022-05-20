package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.BoatDTO;
import com.example.WishAndFish.dto.ReservationDTO;
import com.example.WishAndFish.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/reservations")
@CrossOrigin()
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(value="", method = RequestMethod.GET)
    public List<ReservationDTO> getAll() {
        return this.reservationService.findAll();
    }


    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ReservationDTO getById(@PathVariable Long id) {
        return this.reservationService.getById(id);
    }

    @RequestMapping(value="getAllByCottage/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<ReservationDTO>> getAllByCottage(@PathVariable Long id) {
        List<ReservationDTO> ret = this.reservationService.getAllByCottage(id);
        if(ret == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }

    @RequestMapping(value="getAllByBoat/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<ReservationDTO>> getAllByBoat(@PathVariable Long id) {
        List<ReservationDTO> ret = this.reservationService.getAllByBoat(id);
        if(ret == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }
}

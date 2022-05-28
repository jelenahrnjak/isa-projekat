package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.dto.BoatDTO;
import com.example.WishAndFish.dto.CreateReservationDTO;
import com.example.WishAndFish.dto.ReservationDTO;
import com.example.WishAndFish.dto.SearchClientDTO;
import com.example.WishAndFish.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/reservations")
@CrossOrigin()
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(value="", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER') || hasAuthority('ROLE_BOAT_OWNER') || hasAuthority('ROLE_CLIENT')")
    public List<ReservationDTO> getAll() {
        return this.reservationService.findAll();
    }


    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER') || hasAuthority('ROLE_BOAT_OWNER') || hasAuthority('ROLE_CLIENT')")
    public ReservationDTO getById(@PathVariable Long id) {
        return this.reservationService.getById(id);
    }

    @RequestMapping(value="getAllByCottage/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public ResponseEntity<List<ReservationDTO>> getAllByCottage(@PathVariable Long id) {
        List<ReservationDTO> ret = this.reservationService.getAllByCottage(id);
        if(ret == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }

    @RequestMapping(value="getAllByBoat/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<List<ReservationDTO>> getAllByBoat(@PathVariable Long id) {
        List<ReservationDTO> ret = this.reservationService.getAllByBoat(id);
        if(ret == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }

    @RequestMapping(value="search", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<List<ReservationDTO>> search(SearchClientDTO dto) {
        List<ReservationDTO> ret = this.reservationService.search(dto);
        if(ret == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }

    @RequestMapping(value="searchCottage", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public ResponseEntity<List<ReservationDTO>> searchCottage(SearchClientDTO dto) {
        List<ReservationDTO> ret = this.reservationService.searchCottage(dto);
        if(ret == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }

  
    @RequestMapping(value="/getAllAvailableClientsBoat/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER') || hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<List<ClientDTO>> getAllAvailableClientsBoat(@PathVariable Long id) {
        List<ClientDTO> ret =  this.reservationService.getAllAvailableClientsBoat(id);
        if(ret == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(ret, HttpStatus.OK);
        }
    }

//    @RequestMapping(value="/getNumberofReservationMonthlyCottage/{id}", method = RequestMethod.GET)
//    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
//    public ResponseEntity<Map<String,Integer>> getNumberofReservationMonthlyCottage(@PathVariable Long id) {
//        Map<String,Integer> n =  this.reservationService.getNumberofReservationMonthlyCottage(id);
//        if(n == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        else {
//            return new ResponseEntity<>(n, HttpStatus.OK);
//        }
//    }

    @RequestMapping(value="/getNumberofReservationMonthlyCottage", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public ResponseEntity<Map<String,Integer>> getNumberofReservationMonthlyCottage(@RequestBody MonthReportDTO dto) {
        Map<String,Integer> n =  this.reservationService.getNumberofReservationMonthlyCottage(dto);
        if(n == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(n, HttpStatus.OK);
        }
    }


    @RequestMapping(value="/getNumberofReservationYearlyCottage/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public ResponseEntity<Map<Integer,Integer>> getNumberofReservationYearlyCottage(@PathVariable Long id) {
        Map<Integer,Integer>  n =  this.reservationService.getNumberofReservationYearlyCottage(id);
        if(n == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(n, HttpStatus.OK);
        }
    }

    @RequestMapping(value="/getNumberofReservationWeeklyCottage", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public ResponseEntity<Integer> getNumberofReservationWeeklyCottage(@RequestBody WeekReportDTO dto) {
        Integer n =  this.reservationService.getNumberofReservationWeeklyCottage(dto);
        if(n == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(n, HttpStatus.OK);
        }
    }


    @RequestMapping(value="/getNumberofReservationSpecificWeekCottage", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public ResponseEntity<Map<String, Integer>> getNumberofReservationSpecificWeekCottage(@RequestBody WeekReportDTO dto) {
        Map<String,Integer> n =  this.reservationService.getNumberofReservationSpecificWeekCottage(dto);
        if(n == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(n, HttpStatus.OK);
        }
    }


    @RequestMapping(value="/getIncomeInSpecificPeriod", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public ResponseEntity<Map<String, Double>> getIncomeInSpecificPeriod(@RequestBody WeekReportDTO dto) {
        Map<String,Double> n =  this.reservationService.getIncomeInSpecificPeriod(dto);
        if(n == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(n, HttpStatus.OK);
        }
    }



    @RequestMapping(value="createReservation", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public ResponseEntity<Boolean> createReservation(@RequestBody CreateReservationDTO reservation) throws MessagingException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(reservation.getStart());
        LocalDate end = LocalDate.parse(reservation.getEnd());

        reservation.setStartDate(start.atTime(LocalTime.of(14, 0)));
        reservation.setEndDate(end.atTime(LocalTime.of(12, 0)));

        if(this.reservationService.createReservation(reservation)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

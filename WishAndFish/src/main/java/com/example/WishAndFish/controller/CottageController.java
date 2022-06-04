package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.model.Review;
import com.example.WishAndFish.security.util.TokenUtils;
import com.example.WishAndFish.service.CottageService;
import com.example.WishAndFish.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/cottages")
@CrossOrigin()
public class CottageController {

    @Autowired
    private CottageService cottageService;

    @Autowired
    private ReviewService reviewService;


    @Autowired
    TokenUtils tokenUtils;

    private final Logger logger = LoggerFactory.getLogger(CottageController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<CottageDTO> getAll() {
        return this.cottageService.findAll();
    }

    @RequestMapping(value = "/client/{email}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public List<CottageDTO> getAllClients(@PathVariable String email) {
        return this.cottageService.findAllClient(email);
    }

    @RequestMapping(value = "/search/client/{email}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public List<CottageDTO> searchClient(@PathVariable String email,  CottageDTO data) {
        return this.cottageService.searchClient(data, email);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<CottageDTO> search(CottageDTO dto) {
        return this.cottageService.mapSearch(dto);
    }

    @PostMapping(value = "/addCottage")
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public Cottage addCottage(@RequestBody AddCottageDTO newCottage) {
        return this.cottageService.addCottage(newCottage);
    }

    @DeleteMapping(value = "/deleteCottage/{id}")
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public ResponseEntity<Long> deleteCottage(@PathVariable Long id) {

        Long c = this.cottageService.deleteCottage(id);

        if(c ==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }


    @RequestMapping(value = "/findCottage/{id}", method = RequestMethod.GET)
//    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public Cottage findCottage(@PathVariable Long id) {
        return this.cottageService.findCottage(id);
    }

    @RequestMapping(value = "/editBasicInfo", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public ResponseEntity<EditCottageDTO> editBasicInfo(@RequestBody EditCottageDTO editedCottage) {

        EditCottageDTO c = cottageService.editBasicInfo(editedCottage);

        if(c==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(c,HttpStatus.OK);
    }

    @RequestMapping(value = "/searchAppointments/{email}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public List<CottageDTO> searchAppointments(@PathVariable String email, AppointmentSearchDTO data){

        return this.cottageService.searchAppointments(data, email);
    }

    @RequestMapping(value = "/getAllComments/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public List<ReviewDTO> getAllCommentsCottage(@PathVariable Long id){
        return this.reviewService.getAllCommentsCottage(id);
    }
}



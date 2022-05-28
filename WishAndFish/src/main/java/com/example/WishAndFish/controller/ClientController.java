package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.model.User;
import com.example.WishAndFish.security.util.TokenUtils;
import com.example.WishAndFish.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/clients")
@CrossOrigin()
public class ClientController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/subscribeToCottage", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public ResponseEntity<String> subscribeToCottage(@RequestBody SubscriptionDTO data) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if (clientService.subscribeToCottage(data.cottageId, data.userEmail)) {
            return new ResponseEntity<>("{\"text\": \"Successfully subscribed\"}", httpHeaders, HttpStatus.OK);
        }

        return new ResponseEntity<>("{\"text\": \"Unsuccessful subscription\"}", httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/unsubscribeFromCottage", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public ResponseEntity<String> unsubscribeFromCottage(@RequestBody SubscriptionDTO data) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if (clientService.unsubscribeFromCottage(data.cottageId, data.userEmail)) {
            return new ResponseEntity<>("{\"text\": \"Successfully subscribed\"}", httpHeaders, HttpStatus.OK);
        }

        return new ResponseEntity<>("{\"text\": \"Unsuccessful subscription\"}", httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/subscribeToBoat", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public ResponseEntity<String> subscribeToBoat(@RequestBody SubscriptionDTO data) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if (clientService.subscribeToBoat(data.boatId, data.userEmail)) {
            return new ResponseEntity<>("{\"text\": \"Successfully subscribed\"}", httpHeaders, HttpStatus.OK);
        }

        return new ResponseEntity<>("{\"text\": \"Unsuccessful subscription\"}", httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/unsubscribeFromBoat", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public ResponseEntity<String> unsubscribeFromBoat(@RequestBody SubscriptionDTO data) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if (clientService.unsubscribeFromBoat(data.boatId, data.userEmail)) {
            return new ResponseEntity<>("{\"text\": \"Successfully subscribed\"}", httpHeaders, HttpStatus.OK);
        }

        return new ResponseEntity<>("{\"text\": \"Unsuccessful subscription\"}", httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/subscribeToAdventure", method = RequestMethod.PUT,
            produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public ResponseEntity<String> subscribeToAdventure(@RequestBody SubscriptionDTO data) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if (clientService.subscribeToAdventure(data.adventureId, data.userEmail)) {
            return new ResponseEntity<>("{\"text\": \"Successfully subscribed\"}", httpHeaders, HttpStatus.OK);
        }

        return new ResponseEntity<>("{\"text\": \"Unsuccessful subscription\"}", httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/unsubscribeFromAdventure", method = RequestMethod.PUT,
            produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public ResponseEntity<String> unsubscribeFromAdventure(@RequestBody SubscriptionDTO data) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if (clientService.unsubscribeFromAdventure(data.adventureId, data.userEmail)) {
            return new ResponseEntity<>("{\"text\": \"Successfully subscribed\"}", httpHeaders, HttpStatus.OK);
        }

        return new ResponseEntity<>("{\"text\": \"Unsuccessful subscription\"}", httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/checkSubscription/{type}/{id}/{email}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public boolean checkSubscription(@PathVariable String type,@PathVariable Long id,@PathVariable String email) {

        if (type.equals("boat")) {
            return clientService.checkBoatExistence(email, id);
        } else if (type.equals("cottage")) {
            return clientService.checkCottageExistence(email, id);
        } else if (type.equals("adventure")) {
            return clientService.checkAdventureExistence(email, id);
        }
        return false;
    }

    @RequestMapping(value = "/cottageSubscriptions/{email}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public List<CottageDTO> getAllCottagesSubscriptions(@PathVariable String email) {
        return clientService.getAllCottagesSubscriptions(email);
    }

    @RequestMapping(value = "/boatSubscriptions/{email}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public List<BoatDTO> getAllBoatsSubscriptions(@PathVariable String email) {
        return clientService.getAllBoatsSubscriptions(email);
    }

    @RequestMapping(value = "/adventureSubscriptions/{email}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public List<FishingAdventureDTO> getAllAdventuresSubscriptions(@PathVariable String email) {
        return clientService.getAllAdventuresSubscriptions(email);
    }

}

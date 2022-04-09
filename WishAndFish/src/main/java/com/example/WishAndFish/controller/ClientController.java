package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.SubscriptionDTO;
import com.example.WishAndFish.dto.UserDTO;
import com.example.WishAndFish.model.User;
import com.example.WishAndFish.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/clients")
@CrossOrigin()
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/subscribeToCottage", method = RequestMethod.PUT)
    //@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> subscribeToCottage(@RequestBody SubscriptionDTO data) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if (clientService.subscribeToCottage(data.cottageId, data.userEmail)) {
            return new ResponseEntity<>("{\"text\": \"Successfully subscribed\"}", httpHeaders, HttpStatus.OK);
        }

        return new ResponseEntity<>("{\"text\": \"Unsuccessful subscription\"}", httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/subscribeToBoat", method = RequestMethod.PUT)
    //@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> subscribeToBoat(@RequestBody SubscriptionDTO data) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if (clientService.subscribeToBoat(data.boatId, data.userEmail)) {
            return new ResponseEntity<>("{\"text\": \"Successfully subscribed\"}", httpHeaders, HttpStatus.OK);
        }

        return new ResponseEntity<>("{\"text\": \"Unsuccessful subscription\"}", httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/subscribeToAdventure", method = RequestMethod.PUT,
            produces = "application/json")
    //@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> subscribeToAdventure(@RequestBody SubscriptionDTO data) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if (clientService.subscribeToAdventure(data.adventureId, data.userEmail)) {
            return new ResponseEntity<>("{\"text\": \"Successfully subscribed\"}", httpHeaders, HttpStatus.OK);
        }

        return new ResponseEntity<>("{\"text\": \"Unsuccessful subscription\"}", httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/checkSubscription/{email}/{type}/{id}", method = RequestMethod.GET)
    public boolean getUser(@PathVariable String email, @PathVariable String type, @PathVariable Long id) {

        if (type.equals("boat")) {
            return clientService.checkBoatExistence(email, id);
        } else if (type.equals("cottage")) {
            return clientService.checkCottageExistence(email, id);
        } else if (type.equals("adventure")) {
            return clientService.checkAdventureExistence(email, id);
        }
        return false;
    }

}

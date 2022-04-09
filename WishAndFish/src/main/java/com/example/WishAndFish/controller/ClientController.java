package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.SubscriptionDTO;
import com.example.WishAndFish.dto.UserDTO;
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

@RestController
@RequestMapping(value = "api/clients")
@CrossOrigin()
public class ClientController {

    @Autowired
    private TokenUtils tokenUtils;

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

    @RequestMapping(value = "/checkSubscription/{type}/{id}", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('CLIENT')")
    public boolean getUser(@RequestHeader("Authorization") String token, @PathVariable String type, Long id) {

        String email  = tokenUtils.getEmailFromToken(token.split(" ")[1]);

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

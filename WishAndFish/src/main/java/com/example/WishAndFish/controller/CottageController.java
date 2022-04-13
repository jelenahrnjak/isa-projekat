package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.AddActionDTO;
import com.example.WishAndFish.dto.AddCottageDTO;
import com.example.WishAndFish.dto.BoatDTO;
import com.example.WishAndFish.dto.CottageDTO;
import com.example.WishAndFish.dto.EditCottageDTO;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.security.util.TokenUtils;
import com.example.WishAndFish.service.CottageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    TokenUtils tokenUtils;

    private final Logger logger = LoggerFactory.getLogger(CottageController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<CottageDTO> getAll() {
        return this.cottageService.findAll();
    }

    @RequestMapping(value = "/client/{email}", method = RequestMethod.GET)
    public List<CottageDTO> getAllClients(@PathVariable String email) {
        return this.cottageService.findAllClient(email);
    }

    @RequestMapping(value = "/search/client/{email}", method = RequestMethod.GET)
    public List<CottageDTO> searchClient(@PathVariable String email,  CottageDTO data) {
        return this.cottageService.searchClient(data, email);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<CottageDTO> search(CottageDTO dto) {
        return this.cottageService.search(dto);
    }

    @PostMapping(value = "/addCottage")
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public Cottage addCottage(@RequestBody AddCottageDTO newCottage) {
        return this.cottageService.addCottage(newCottage);
    }

    @DeleteMapping(value = "/deleteCottage/{id}")
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<Long> deleteCottage(@PathVariable Long id) {
        return this.cottageService.deleteCottage(id);
    }


    @RequestMapping(value = "/findCottage/{id}", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public Cottage findCottage(@PathVariable Long id) {
        return this.cottageService.findCottage(id);
    }

    @RequestMapping(value = "/editBasicInfo", method = RequestMethod.PUT)
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public EditCottageDTO editBasicInfo(@RequestBody EditCottageDTO editedCottage) {
        return this.cottageService.editBasicInfo(editedCottage);
    }

}



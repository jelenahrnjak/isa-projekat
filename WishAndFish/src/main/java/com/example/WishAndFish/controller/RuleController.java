package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.AdditionalServicesDTO;
import com.example.WishAndFish.dto.RuleDTO;
import com.example.WishAndFish.dto.UserDTO;
import com.example.WishAndFish.service.BoatService;
import com.example.WishAndFish.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/rules")
@CrossOrigin()
public class RuleController {

    @Autowired
    private RuleService ruleService;


    @RequestMapping(value="/getAllByCottage/{id}", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public List<RuleDTO> getAllByCottage(@PathVariable Long id) {
        return this.ruleService.getAllByCottage(id);
    }

    @DeleteMapping(value="/deleteRule/{id}")
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<Long> deleteRule(@PathVariable Long id) {
        return this.ruleService.deleteRule(id);
    }

}

package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.RuleDTO;
import com.example.WishAndFish.model.Rule;
import com.example.WishAndFish.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/rules")
@CrossOrigin()
public class RuleController {

    @Autowired
    private RuleService ruleService;


    @RequestMapping(value="/getAllByCottage/{id}", method = RequestMethod.GET)
//    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public List<RuleDTO> getAllByCottage(@PathVariable Long id) {
        return this.ruleService.getAllByCottage(id);
    }

    @RequestMapping(value="/getAllByBoat/{id}", method = RequestMethod.GET)
//    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public List<RuleDTO> getAllByBoat(@PathVariable Long id) {
        return this.ruleService.getAllByBoat(id);
    }

    @DeleteMapping(value="/deleteRule/{id}")
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER') || hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<Long> deleteRule(@PathVariable Long id) {
        return this.ruleService.deleteRule(id);
    }

    @RequestMapping(value="/addRule", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public Rule addRule(@RequestBody RuleDTO dto) {
        return this.ruleService.addRule(dto);
    }

    @RequestMapping(value="/addRuleBoat", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<Rule> addRuleBoat(@RequestBody RuleDTO dto) {
        Rule r = this.ruleService.addRuleBoat(dto);
        if(r!=null){
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}

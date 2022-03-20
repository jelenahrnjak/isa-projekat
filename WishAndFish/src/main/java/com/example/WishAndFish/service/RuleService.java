package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.BoatDTO;
import com.example.WishAndFish.dto.RuleDTO;
import com.example.WishAndFish.model.AdditionalService;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.Rule;
import com.example.WishAndFish.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    public List<RuleDTO> getAllByCottage(Long id) {

        List<RuleDTO> ret = new ArrayList<RuleDTO>();
        for(Rule r : ruleRepository.findAll()){
            if(!r.getDeleted() && id.equals(r.getCottage().getId())){
                ret.add(new RuleDTO(r));
            }
        };
        return ret;
    }


    public ResponseEntity<Long> deleteRule(Long id){
        for(Rule r: ruleRepository.findAll()){
            if(r.getId() == id) {
                r.setDeleted(true);
                this.ruleRepository.save(r);
                return new ResponseEntity<>(id, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
    }
}

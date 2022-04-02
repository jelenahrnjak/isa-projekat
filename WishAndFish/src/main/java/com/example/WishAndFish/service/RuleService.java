package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.AdditionalServicesDTO;
import com.example.WishAndFish.dto.BoatDTO;
import com.example.WishAndFish.dto.RuleDTO;
import com.example.WishAndFish.model.AdditionalService;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.model.Rule;
import com.example.WishAndFish.repository.CottageRepository;
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

    @Autowired
    private CottageRepository cottageRepository;

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

    public Rule addRule(RuleDTO dto){
        for(Cottage c: this.cottageRepository.findAll()){
            if(dto.getId().equals(c.getId())){
                Rule r = new Rule();
                r.setContent(dto.getContent());
                r.setDeleted(false);
                r.setCottage(c);
                this.ruleRepository.save(r);
                return r;
            }
        }
        return null;
    }
}

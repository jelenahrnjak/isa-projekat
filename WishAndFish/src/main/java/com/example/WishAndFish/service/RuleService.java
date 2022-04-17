package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.RuleDTO;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.model.Rule;
import com.example.WishAndFish.repository.BoatRepository;
import com.example.WishAndFish.repository.CottageRepository;
import com.example.WishAndFish.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BoatRepository boatRepository;

    public List<RuleDTO> getAllByCottage(Long id) {

        List<RuleDTO> ret = new ArrayList<>();
        for(Rule r : ruleRepository.findAll()){
            if(r.getCottage() != null){
                if(!r.getDeleted() && id.equals(r.getCottage().getId())){
                    ret.add(new RuleDTO(r));
                }
            }
        };
        return ret;
    }

    public List<RuleDTO> getAllByBoat(Long id) {

        List<RuleDTO> ret = new ArrayList<>();
        for(Rule r : ruleRepository.findAll()){
            if(r.getBoat() != null){
                if(!r.getDeleted() && id.equals(r.getBoat().getId())){
                    ret.add(new RuleDTO(r));
                }
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

    public Rule addRuleBoat(RuleDTO dto){
        for(Boat b: this.boatRepository.findAll()){
            if(dto.getId().equals(b.getId())){
                Rule r = new Rule();
                r.setContent(dto.getContent());
                r.setDeleted(false);
                r.setBoat(b);
                this.ruleRepository.save(r);
                return r;
            }
        }
        return null;
    }
}

package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.BoatDTO;
import com.example.WishAndFish.dto.RuleDTO;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.Rule;
import com.example.WishAndFish.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
}

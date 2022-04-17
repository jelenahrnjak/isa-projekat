package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.NavigationEquipmentDTO;
import com.example.WishAndFish.dto.RuleDTO;
import com.example.WishAndFish.model.NavigationEquipment;
import com.example.WishAndFish.model.Rule;
import com.example.WishAndFish.repository.NavigationEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NavigationEquipmentService {

    @Autowired
    NavigationEquipmentRepository navigationEquipmentRepository;

    public List<NavigationEquipmentDTO> getAllByBoat(Long id) {

        List<NavigationEquipmentDTO> ret = new ArrayList<>();
        for(NavigationEquipment ne : navigationEquipmentRepository.findAll()){
            if(ne.getBoat() != null){
                if(!ne.isDeleted() && id.equals(ne.getBoat().getId())){
                    ret.add(new NavigationEquipmentDTO(ne));
                }
            }
        };
        return ret;
    }
}

package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.NavigationEquipmentDTO;
import com.example.WishAndFish.dto.RuleDTO;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.FishingEquipment;
import com.example.WishAndFish.model.NavigationEquipment;
import com.example.WishAndFish.model.Rule;
import com.example.WishAndFish.repository.BoatRepository;
import com.example.WishAndFish.repository.NavigationEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NavigationEquipmentService {

    @Autowired
    NavigationEquipmentRepository navigationEquipmentRepository;

    @Autowired
    BoatRepository boatRepository;

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


    public NavigationEquipment addNavigationEquipment(NavigationEquipmentDTO dto){
        for(Boat b: this.boatRepository.findAll()){
            if(dto.getId().equals(b.getId())){
                NavigationEquipment ne = new NavigationEquipment();
                ne.setName(dto.getName());
                ne.setDeleted(false);
                ne.setBoat(b);
                this.navigationEquipmentRepository.save(ne);
                return ne;
            }
        }
        return null;
    }


    public ResponseEntity<Long> deleteNavigationEquipment(Long id){
        for(NavigationEquipment r: navigationEquipmentRepository.findAll()){
            if(r.getId() == id) {
                r.setDeleted(true);
                this.navigationEquipmentRepository.save(r);
                return new ResponseEntity<>(id, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
    }
}

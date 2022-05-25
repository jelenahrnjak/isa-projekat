package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.FishingEquipmentDTO;
import com.example.WishAndFish.dto.NavigationEquipmentDTO;
import com.example.WishAndFish.model.Boat;
import com.example.WishAndFish.model.FishingEquipment;
import com.example.WishAndFish.model.NavigationEquipment;
import com.example.WishAndFish.model.Rule;
import com.example.WishAndFish.repository.BoatRepository;
import com.example.WishAndFish.repository.FishingEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FishingEquipmentService {

    @Autowired
    FishingEquipmentRepository fishingEquipmentRepository;

    @Autowired
    BoatRepository boatRepository;

    public List<FishingEquipmentDTO> getAllByBoat(Long id) {

        List<FishingEquipmentDTO> ret = new ArrayList<>();
        for(FishingEquipment fe : fishingEquipmentRepository.findAll()){
            if(fe.getBoat() != null){
                if(!fe.isDeleted() && id.equals(fe.getBoat().getId())){
                    ret.add(new FishingEquipmentDTO(fe));
                }
            }
        };
        return ret;
    }

    public FishingEquipment addFishingEquipment(FishingEquipmentDTO dto){
        for(Boat b: this.boatRepository.findAll()){
            if(dto.getId().equals(b.getId())){
                FishingEquipment fe = new FishingEquipment();
                fe.setName(dto.getName());
                fe.setDeleted(false);
                fe.setBoat(b);
                this.fishingEquipmentRepository.save(fe);
                return fe;
            }
        }
        return null;
    }



    public ResponseEntity<Long> deleteFishingEquipment(Long id){
        for(FishingEquipment r: fishingEquipmentRepository.findAll()){
            if(r.getId() == id) {
                r.setDeleted(true);
                this.fishingEquipmentRepository.save(r);
                return new ResponseEntity<>(id, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
    }

}

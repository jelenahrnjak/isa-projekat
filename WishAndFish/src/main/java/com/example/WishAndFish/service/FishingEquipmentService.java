package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.FishingEquipmentDTO;
import com.example.WishAndFish.dto.NavigationEquipmentDTO;
import com.example.WishAndFish.model.FishingEquipment;
import com.example.WishAndFish.model.NavigationEquipment;
import com.example.WishAndFish.repository.FishingEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FishingEquipmentService {

    @Autowired
    FishingEquipmentRepository fishingEquipmentRepository;

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

}

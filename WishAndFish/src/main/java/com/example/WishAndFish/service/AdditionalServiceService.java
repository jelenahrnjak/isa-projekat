package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.AdditionalServicesDTO;
import com.example.WishAndFish.model.AdditionalService;
import com.example.WishAndFish.model.Appointment;
import com.example.WishAndFish.repository.AdditionalServiceRepository;
import com.example.WishAndFish.repository.CottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdditionalServiceService {
    @Autowired
    private CottageRepository cottageRepository;

    @Autowired
    private AdditionalServiceRepository additionalServiceRepository;


    public List<AdditionalServicesDTO> getAllByCottage(Long id){
        List<AdditionalServicesDTO> ret = new ArrayList<AdditionalServicesDTO>();
        for(AdditionalService as: additionalServiceRepository.findAll()){
            for(Appointment a: as.getAppointments()){
                if(id.equals(a.getCottage().getId())){
                    ret.add(new AdditionalServicesDTO(as));
                }
            }
        }
        return ret;
    }
}

package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.AddCottageImageDTO;
import com.example.WishAndFish.dto.AdditionalServicesDTO;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.AdditionalServiceRepository;
import com.example.WishAndFish.repository.CottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//            for(Appointment a: as.getAppointments()){
//                if(id.equals(a.getCottage().getId()) && !as.getDeleted()){
//                    ret.add(new AdditionalServicesDTO(as));
//                }
//            }

            if(id.equals(as.getCottage().getId()) && !as.getDeleted()){
                ret.add(new AdditionalServicesDTO(as));
            }
        }
        return ret;
    }



    public ResponseEntity<Long> deleteAdditionalServices(Long id){
        for(AdditionalService as: additionalServiceRepository.findAll()){
            if(as.getId() == id) {
                as.setDeleted(true);
                this.additionalServiceRepository.save(as);
                return new ResponseEntity<>(id, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
    }

    public AdditionalService addAdditionalService(AdditionalServicesDTO dto){
        for(Cottage c: this.cottageRepository.findAll()){
            if(dto.getId().equals(c.getId())){
                AdditionalService as = new AdditionalService();
                as.setName(dto.getName());
                as.setPrice(dto.getPrice());
                as.setDeleted(false);
                as.setCottage(c);
                this.additionalServiceRepository.save(as);
                return as;
            }
        }
        return null;
    }


    public List<AdditionalServicesDTO> findAdditionalServicesForAppointment(Long id){
        List<AdditionalServicesDTO> ret = new ArrayList<AdditionalServicesDTO>();
        for(AdditionalService as: this.additionalServiceRepository.findAll()){
            for(Appointment a: as.getCottage().getAppointments()){
                if(id.equals(a.getId()) && !a.isDeleted()){
                    ret.add(new AdditionalServicesDTO(as));
                }
            }
        }

        return ret;
    }
}

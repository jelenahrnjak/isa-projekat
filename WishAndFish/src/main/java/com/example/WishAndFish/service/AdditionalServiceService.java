package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.AdditionalServicesDTO;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.AdditionalServiceRepository;
import com.example.WishAndFish.repository.AppointmentRepository;
import com.example.WishAndFish.repository.BoatRepository;
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

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private BoatRepository boatRepository;

    public List<AdditionalServicesDTO> getAllByCottage(Long id) {
        List<AdditionalServicesDTO> ret = new ArrayList<>();
        for (AdditionalService as : additionalServiceRepository.findAll()) {
            if (as.getCottage() != null) {
                if ((id.equals(as.getCottage().getId()) && !as.getDeleted())) {
                    ret.add(new AdditionalServicesDTO(as));
                }
            }
        }
        return ret;
    }


    public ResponseEntity<Long> deleteAdditionalServices(Long id) {
        for (AdditionalService as : additionalServiceRepository.findAll()) {
            if (as.getId() == id) {
                as.setDeleted(true);
                this.additionalServiceRepository.save(as);
                return new ResponseEntity<>(id, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
    }

    public AdditionalService addAdditionalService(AdditionalServicesDTO dto) {
        for (Cottage c : this.cottageRepository.findAll()) {
            if (dto.getId().equals(c.getId())) {
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

    public AdditionalService addAdditionalServiceBoat(AdditionalServicesDTO dto) {
        for (Boat b : this.boatRepository.findAll()) {
            if (dto.getId().equals(b.getId())) {
                AdditionalService as = new AdditionalService();
                as.setName(dto.getName());
                as.setPrice(dto.getPrice());
                as.setDeleted(false);
                as.setBoat(b);
                this.additionalServiceRepository.save(as);
                return as;
            }
        }
        return null;
    }
//
//    public List<AdditionalServicesDTO> findAdditionalServicesForAppointment(Long id){
//        List<AdditionalServicesDTO> ret = new ArrayList<>();
//        for(AdditionalService as: this.additionalServiceRepository.findAll()){
//            if(as.getAppointment() != null){
//                if(id.equals(as.getAppointment().getId())){
//                    ret.add(new AdditionalServicesDTO(as));
//                }
//            }
//
//        }
//        return ret;
//    }


    public List<AdditionalServicesDTO> findAdditionalServicesForAppointment(Long id) {
        List<AdditionalServicesDTO> ret = new ArrayList<>();
        for (AdditionalService as : this.additionalServiceRepository.findAll()) {
//            if(as.getAppointment() != null){
//                if(id.equals(as.getAppointment().getId())){
//                    ret.add(new AdditionalServicesDTO(as));
//                }
//            }

            for (Appointment app : as.getAppointments()) {
                if (id.equals(app.getId()) && !as.getDeleted()) {
                    ret.add(new AdditionalServicesDTO(as));
                }
            }

        }
        return ret;
    }


    public List<AdditionalServicesDTO> getAllByBoat(Long id) {
        List<AdditionalServicesDTO> ret = new ArrayList<>();
        for (AdditionalService as : additionalServiceRepository.findAll()) {
            if (as.getBoat() != null) {
                if ((id.equals(as.getBoat().getId()) && !as.getDeleted())) {
                    ret.add(new AdditionalServicesDTO(as));
                }
            }
        }
        return ret;
    }

    public List<AdditionalServicesDTO> getAllByAdventure(Long id) {
        List<AdditionalServicesDTO> ret = new ArrayList<>();
        for (AdditionalService as : additionalServiceRepository.findAll()) {
            if (as.getFishingAdventure() != null) {
                if ((id.equals(as.getFishingAdventure().getId()) && !as.getDeleted())) {
                    ret.add(new AdditionalServicesDTO(as));
                }
            }
        }
        return ret;
    }

    public List<AdditionalServicesDTO> getAllByAppointment(Long appointment) {

        List<AdditionalServicesDTO> ret = new ArrayList<>();
        for (AdditionalService as : additionalServiceRepository.findAll()) {
            for (Appointment a : as.getAppointments()) {
                if (a.getId() == appointment) {
                    ret.add(new AdditionalServicesDTO(as));
                }
            }
        }
        return ret;
    }
}

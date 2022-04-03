package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.AdditionalServicesDTO;
import com.example.WishAndFish.dto.AppointmentDTO;
import com.example.WishAndFish.model.AdditionalService;
import com.example.WishAndFish.model.Appointment;
import com.example.WishAndFish.model.Rule;
import com.example.WishAndFish.repository.AppointmentRepository;
import com.example.WishAndFish.repository.CottageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<AppointmentDTO> getAllByCottage(Long id){
        List<AppointmentDTO> ret = new ArrayList<AppointmentDTO>();
        for(Appointment as: appointmentRepository.findAll()){
            if(id.equals(as.getCottage().getId()) && !as.getReserved() && !as.isDeleted()){
                ret.add(new AppointmentDTO(as));
            }
        }
        return ret;
    }

    public ResponseEntity<Long> deleteAppointment(Long id){
        for(Appointment a: this.appointmentRepository.findAll()){
            if(a.getId() == id) {
                a.setDeleted(true);
                this.appointmentRepository.save(a);
                return new ResponseEntity<>(id, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
    }




}

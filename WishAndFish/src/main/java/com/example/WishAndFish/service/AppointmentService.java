package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CottageRepository cottageRepository;

    @Autowired
    private AdditionalServiceRepository additionalServiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    BoatRepository boatRepository;

    public List<AppointmentDTO> getAllByCottage(Long id){
        List<AppointmentDTO> ret = new ArrayList<>();
        for(Appointment as: appointmentRepository.findAll()){
            if(as.getCottage() != null){
                if(id.equals(as.getCottage().getId()) && !as.getReserved() && !as.isDeleted() && as.getIsAction()){
                    ret.add(new AppointmentDTO(as));
                }
            }
        }
        return ret;
    }

    public List<AppointmentDTO> getAllByBoat(Long id){
        List<AppointmentDTO> ret = new ArrayList<>();
        for(Appointment as: appointmentRepository.findAll()){
            if(as.getBoat() != null){
                if(id.equals(as.getBoat().getId()) && !as.getReserved() && !as.isDeleted() && as.getIsAction()){
                    ret.add(new AppointmentDTO(as));
                }
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


    public Appointment editAvailability(AvailabilityDTO dto){

        LocalDateTime end = findDate(dto.getEndDate());
        LocalDateTime start = findDate(dto.getStartDate());

        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getStartDate().isBefore(end) && r.getAppointment().getStartDate().isAfter(start)){
                return null;
            }
        }

        Appointment a = new Appointment();
        a.setDeleted(false);
        a.setCottage(cottageRepository.findById(dto.getId()).orElseGet(null));
        a.setIsAction(false);
        a.setStartDate(findDate(dto.getStartDate()));
        a.setEndDate(findDate(dto.getEndDate()));
        a.setDuration(Duration.between(findDate(dto.getStartDate()), findDate(dto.getEndDate())));
        a.setReserved(false);
        this.appointmentRepository.save(a);
        return a;
    }

    public Appointment editAvailabilityBoat(AvailabilityDTO dto){

        LocalDateTime end = findDate(dto.getEndDate());
        LocalDateTime start = findDate(dto.getStartDate());

        for(Reservation r: reservationRepository.findAll()){
            if((r.getAppointment().getStartDate().isBefore(end) && r.getAppointment().getStartDate().isAfter(start)) || (r.getAppointment().getStartDate().isBefore(end) && r.getAppointment().getEndDate().isAfter(end))){
                return null;
            }
        }

        Appointment a = new Appointment();
        a.setDeleted(false);
        a.setBoat(boatRepository.findById(dto.getId()).orElseGet(null));
        a.setIsAction(false);
        a.setStartDate(findDate(dto.getStartDate()));
        a.setEndDate(findDate(dto.getEndDate()));
        a.setDuration(Duration.between(start, end));
        a.setReserved(false);
        this.appointmentRepository.save(a);
        return a;
    }

    private LocalDateTime findDate(String start){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(start, formatter);
    }


    public Appointment addNewAction(AddActionDTO dto) throws MessagingException {
        Appointment a = new Appointment();
        a.setIsAction(true);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        a.setStartDate(LocalDateTime.parse(dto.getStartDate(), formatter));
        a.setEndDate(LocalDateTime.parse(dto.getEndDate(), formatter));
        a.setExpirationDate(LocalDateTime.parse(dto.getExpirationDate(), formatter));
        a.setCottage(cottageRepository.findById(dto.getId()).orElseGet(null));
        a.setMaxPersons(dto.getMaxPersons());
        a.setPrice(dto.getPrice());
        appointmentRepository.save(a);

        for(Long as: dto.getAdditionalServices()){
            AdditionalService add = additionalServiceRepository.findById(as).orElseGet(null);
            add.getAppointments().add(a);
            additionalServiceRepository.save(add);
            //a.getAdditionalServices().add(additionalServiceRepository.findById(as).orElseGet(null));
        }
//        appointmentRepository.save(a);

        for(Client c: clientRepository.findAll()){
            for(Cottage cot: c.getCottageSubscriptions()){
                if(dto.getId().equals(cot.getId())){
                    emailService.sendEmailForNewAction(c.getEmail(), cot.getName());
                }
            }
        }
        return a;
    }



    public Appointment addNewActionBoat(AddActionDTO dto) throws MessagingException {
        Appointment a = new Appointment();
        a.setIsAction(true);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        a.setStartDate(LocalDateTime.parse(dto.getStartDate(), formatter));
        a.setEndDate(LocalDateTime.parse(dto.getEndDate(), formatter));
        a.setExpirationDate(LocalDateTime.parse(dto.getExpirationDate(), formatter));
        a.setBoat(boatRepository.findById(dto.getId()).orElseGet(null));
        a.setMaxPersons(dto.getMaxPersons());
        a.setPrice(dto.getPrice());
        appointmentRepository.save(a);

        for(Long as: dto.getAdditionalServices()){
//            AdditionalService aservice = additionalServiceRepository.findById(as).orElseGet(null);
//            aservice.setBoat(boatRepository.getById(dto.getId()));
//            additionalServiceRepository.save(aservice);
//            a.getAdditionalServices().add(additionalServiceRepository.findById(as).orElseGet(null));
            AdditionalService add = additionalServiceRepository.findById(as).orElseGet(null);
            add.getAppointments().add(a);
            additionalServiceRepository.save(add);
        }

        for(Client c: clientRepository.findAll()){
            for(Boat boat: c.getBoatSubscriptions()){
                if(dto.getId().equals(boat.getId())){
                    emailService.sendEmailForNewActionBoat(c.getEmail(), boat.getName());
                }
            }
        }
        return a;
    }

}

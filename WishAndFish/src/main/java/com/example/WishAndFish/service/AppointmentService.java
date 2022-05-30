package com.example.WishAndFish.service;

import com.example.WishAndFish.dto.*;
import com.example.WishAndFish.model.*;
import com.example.WishAndFish.repository.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Autowired
    FishingAdventureRepository fishingAdventureRepository;

    public List<AppointmentDTO> getAllByCottage(Long id){
        List<AppointmentDTO> ret = new ArrayList<>();
        for(Appointment as: appointmentRepository.findAll()){
            if(as.getCottage() != null){
                if(id.equals(as.getCottage().getId()) && !as.getReserved() && !as.isDeleted()){
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
                if(id.equals(as.getBoat().getId()) && !as.getReserved() && !as.isDeleted()){
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

        //ako postoji rezervacija u tom periodu
        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getCottage() != null){
                if(dto.getId().equals(r.getAppointment().getCottage().getId()) && ((start.isAfter(r.getAppointment().getStartDate()) && start.isBefore(r.getAppointment().getEndDate())) || (end.isAfter(r.getAppointment().getStartDate()) && end.isBefore(r.getAppointment().getEndDate())))){
                    return null;
                }
                else if(r.getAppointment().getStartDate().isAfter(start) && r.getAppointment().getEndDate().isBefore(end)){
                    return null;
                }
            }
        }

        //ako postoji vec slobodan termin u tom periodu
        for(Appointment a: appointmentRepository.findAll()){
            if(a.getCottage() != null){
                if(dto.getId().equals(a.getCottage().getId()) && ((start.isAfter(a.getStartDate()) && start.isBefore(a.getEndDate())) || (end.isAfter(a.getStartDate()) && end.isBefore(a.getEndDate())))){
                    return null;
                }
                else if(a.getStartDate().isAfter(start) && a.getEndDate().isBefore(end)){
                    return null;
                }
            }
        }


        Appointment sameStart = null;
        Appointment sameEnd = null;

        for(Appointment a: appointmentRepository.findAll()){
            if(!a.isDeleted() && !a.getIsAction() && !a.getReserved() && a.getEndDate().plusHours(2).isEqual(start)){
                sameStart = a;
            }

            if(!a.isDeleted() && !a.getIsAction() && !a.getReserved() && a.getStartDate().isEqual(end.plusHours(2))){
                sameEnd = a;
            }
        }


        if(sameEnd == null && sameStart != null){
            sameStart.setEndDate(end);
            appointmentRepository.save(sameStart);
            return sameStart;
        }
        else if(sameStart == null && sameEnd != null){
            sameEnd.setStartDate(start);
            appointmentRepository.save(sameEnd);
            return sameEnd;
        }
        else if(sameStart != null && sameEnd != null){
            sameStart.setEndDate(sameEnd.getEndDate());
            appointmentRepository.save(sameStart);
            appointmentRepository.delete(sameEnd);
            return sameStart;
        }
        Appointment a = new Appointment();
            a.setDeleted(false);
            a.setCottage(cottageRepository.findById(dto.getId()).orElseGet(null));
            a.setIsAction(false);
            a.setStartDate(findDate(dto.getStartDate()));
            a.setEndDate(findDate(dto.getEndDate()));
            a.setDuration(Duration.between(findDate(dto.getStartDate()), findDate(dto.getEndDate())));
            a.setReserved(false);
            a.setPrice(cottageRepository.findById(dto.getId()).orElseGet(null).getPricePerDay());
            a.setMaxPersons(cottageRepository.findById(dto.getId()).orElseGet(null).getNumberOfRooms() * cottageRepository.findById(dto.getId()).orElseGet(null).getBedsPerRoom());
            this.appointmentRepository.save(a);


        return a;

    }

    public Appointment editAvailabilityBoat(AvailabilityDTO dto){

        LocalDateTime end = findDate(dto.getEndDate());
        LocalDateTime start = findDate(dto.getStartDate());

        //ako postoji rezervacija u tom periodu
        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getBoat() != null){
                if(dto.getId().equals(r.getAppointment().getBoat().getId()) && ((start.isAfter(r.getAppointment().getStartDate()) && start.isBefore(r.getAppointment().getEndDate())) || (end.isAfter(r.getAppointment().getStartDate()) && end.isBefore(r.getAppointment().getEndDate())))){
                    return null;
                }
                else if(r.getAppointment().getStartDate().isAfter(start) && r.getAppointment().getEndDate().isBefore(end)){
                    return null;
                }
            }
        }

        //ako postoji vec slobodan termin u tom periodu
        for(Appointment a: appointmentRepository.findAll()){
            if(a.getBoat() != null){
                if(dto.getId().equals(a.getBoat().getId()) && ((start.isAfter(a.getStartDate()) && start.isBefore(a.getEndDate())) || (end.isAfter(a.getStartDate()) && end.isBefore(a.getEndDate())))){
                    return null;
                }
                else if(a.getStartDate().isAfter(start) && a.getEndDate().isBefore(end)){
                    return null;
                }
            }
        }


        Appointment sameStart = null;
        Appointment sameEnd = null;

        for(Appointment a: appointmentRepository.findAll()){
            if(!a.isDeleted() && !a.getIsAction() && !a.getReserved() && a.getEndDate().plusHours(2).isEqual(start)){
                sameStart = a;
            }

            if(!a.isDeleted() && !a.getIsAction() && !a.getReserved() && a.getStartDate().isEqual(end.plusHours(2))){
                sameEnd = a;
            }
        }


        if(sameEnd == null && sameStart != null){
            sameStart.setEndDate(end);
            appointmentRepository.save(sameStart);
            return sameStart;
        }
        else if(sameStart == null && sameEnd != null){
            sameEnd.setStartDate(start);
            appointmentRepository.save(sameEnd);
            return sameEnd;
        }
        else if(sameStart != null && sameEnd != null){
            sameStart.setEndDate(sameEnd.getEndDate());
            appointmentRepository.save(sameStart);
            appointmentRepository.delete(sameEnd);
            return sameStart;
        }
        Appointment a = new Appointment();
        a.setDeleted(false);
        a.setBoat(boatRepository.findById(dto.getId()).orElseGet(null));
        a.setIsAction(false);
        a.setStartDate(findDate(dto.getStartDate()));
        a.setEndDate(findDate(dto.getEndDate()));
        a.setDuration(Duration.between(findDate(dto.getStartDate()), findDate(dto.getEndDate())));
        a.setReserved(false);
        a.setPrice(boatRepository.findById(dto.getId()).orElseGet(null).getPricePerDay());
        a.setMaxPersons(boatRepository.findById(dto.getId()).orElseGet(null).getCapacity());
        this.appointmentRepository.save(a);


        return a;

//        Appointment a = new Appointment();
//        a.setDeleted(false);
//        a.setBoat(boatRepository.findById(dto.getId()).orElseGet(null));
//        a.setIsAction(false);
//        a.setStartDate(findDate(dto.getStartDate()));
//        a.setEndDate(findDate(dto.getEndDate()));
//        a.setDuration(Duration.between(start, end));
//        a.setReserved(false);
//        this.appointmentRepository.save(a);
//        return a;
    }

    private LocalDateTime findDate(String start){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(start, formatter);
    }


    public Appointment addNewAction(AddActionDTO dto) throws MessagingException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime start = LocalDateTime.parse(dto.getStartDate(), formatter);
        LocalDateTime end = LocalDateTime.parse(dto.getEndDate(), formatter);

        //ako postoji rezervacija
        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getCottage() != null){
                if(start.isAfter(r.getAppointment().getStartDate()) && end.isBefore(r.getAppointment().getEndDate()) && dto.getId().equals(r.getAppointment().getCottage().getId())){
                    return null;
                }
            }
        }

        Appointment a = new Appointment();
        a.setIsAction(true);
        a.setStartDate(start);
        a.setEndDate(end);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime start = LocalDateTime.parse(dto.getStartDate(), formatter);
        LocalDateTime end = LocalDateTime.parse(dto.getEndDate(), formatter);

        //ako postoji rezervacija
        for(Reservation r: reservationRepository.findAll()){
            if(r.getAppointment().getBoat() != null){
                if(start.isAfter(r.getAppointment().getStartDate()) && end.isBefore(r.getAppointment().getEndDate()) && dto.getId().equals(r.getAppointment().getBoat().getId())){
                    return null;
                }
            }
        }

        //ako postoji slobodan termin
        for(Appointment free: appointmentRepository.findAll()){
            if(free.getBoat() != null){
                if((start.isAfter(free.getStartDate()) && end.isBefore(free.getEndDate())) && dto.getId().equals(free.getBoat().getId())){

                    //podijeli pronadjeni termin na 2 slobodna i 1 zauzeti
                    free.setEndDate(start); //kraj prvog
                    Appointment newApp = new Appointment(free); //treci koji se dobije
                    newApp.setStartDate(end);
                    newApp.setEndDate(free.getEndDate());
                    appointmentRepository.save(free);
                    appointmentRepository.save(newApp);


                    Appointment a = new Appointment();
                    a.setIsAction(true);
                    a.setStartDate(start);
                    a.setEndDate(end);
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
        }


        return null;
    }


    public  Appointment createReservation(CreateReservationDTO dto){

        Set<Appointment> appointments = new HashSet<>();

        if(dto.getEntity() == 1){

            Cottage cottage = cottageRepository.findById(dto.getEntityId()).orElseGet(null);
            if(cottage == null || cottage.isDeleted()){
                return null;
            }
            appointments = cottage.getAppointments();

        }else if(dto.getEntity() == 2){

            Boat boat = boatRepository.findById(dto.getEntityId()).orElseGet(null);
            if(boat == null || boat.isDeleted()){
                return null;
            }
            appointments = boat.getAppointments();

        }else if(dto.getEntity() == 3){

            FishingAdventure adventure = fishingAdventureRepository.findById(dto.getEntityId()).orElseGet(null);
            if(adventure == null || adventure.isDeleted()){
                return null;
            }
            appointments = adventure.getAppointments();

        }


        return reserveEntity(appointments, dto);

    }

    private Appointment reserveEntity(Set<Appointment> appointments, CreateReservationDTO dto) {

        Long appointmentId = checkAvailability(appointments, dto);
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseGet(null);

        if(appointment == null){
            return  null;
        }

        getBeforeAppointment(appointment, dto);
        getAfterAppointment(appointment,dto);
        appointment.setPrice(dto.getTotalPrice());
        return editAppointmentForReservation(appointment,dto);

    }

    private Appointment getBeforeAppointment(Appointment appointment, CreateReservationDTO dto) {

        if(dto.getStartDate().isAfter(appointment.getStartDate())){
            Appointment newAppointment = new Appointment(appointment);
            newAppointment.setEndDate(dto.getStartDate().with(LocalTime.of(12, 0)));
            newAppointment.setReserved(false);
            appointmentRepository.save(newAppointment);
            return newAppointment;
        }

        return null;
    }

    private Appointment getAfterAppointment(Appointment appointment, CreateReservationDTO dto) {

        if(appointment.getEndDate().isAfter(dto.getEndDate())){
            Appointment newAppointment = new Appointment(appointment);
            newAppointment.setStartDate(dto.getEndDate().with(LocalTime.of(14, 0)));
            newAppointment.setReserved(false);
            appointmentRepository.save(newAppointment);
            return newAppointment;
        }
        return null;
    }
 
    private Long checkAvailability(Set<Appointment> appointments, CreateReservationDTO criteria) {

        for(Appointment a : appointments){
            if(a.getReserved() || a.isDeleted() || a.getIsAction()){

                continue;
            }
            if(a.getStartDate().toLocalDate().isAfter(criteria.getStartDate().toLocalDate())){
                continue;
            }
            if(a.getEndDate().toLocalDate().isBefore(criteria.getEndDate().toLocalDate())){
                continue;
            }
            return a.getId();
        }

        return null;
    }

    public Appointment editAppointmentForReservation(Appointment appointment, CreateReservationDTO dto){

        for(Appointment a : appointmentRepository.findAll()){
            if(a.getId() == appointment.getId()){
                a.setReserved(true);
                a.setStartDate(dto.getStartDate());
                a.setEndDate(dto.getEndDate());
                a.setPrice(dto.getTotalPrice());
                Appointment newAppointment = this.appointmentRepository.save(a);
                mapAdditionalServices(dto.getAdditionalServices(), newAppointment);
                return newAppointment;
            }
        }
        return null;
    }

    private Set<AdditionalService> mapAdditionalServices(ArrayList<AdditionalServicesDTO> additionalServices, Appointment appointment) {

        Set<AdditionalService> ret = new HashSet<>();

        for(AdditionalServicesDTO a : additionalServices){
            AdditionalService mapped = additionalServiceRepository.findById(a.getId()).orElseGet(null);
            if(mapped != null){
                mapped.getAppointments().add(appointment);
                additionalServiceRepository.save(mapped);
                ret.add(mapped);
            }
        }

        return ret;
    }
}

package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.AddActionDTO;
import com.example.WishAndFish.dto.AppointmentDTO;
import com.example.WishAndFish.dto.AvailabilityDTO;
import com.example.WishAndFish.model.AdditionalService;
import com.example.WishAndFish.model.Appointment;
import com.example.WishAndFish.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping(value = "api/appointments")
@CrossOrigin()
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value="/getAllByCottage/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public List<AppointmentDTO> getAllByCottage(@PathVariable Long id) {
        List<AppointmentDTO> list = this.appointmentService.getAllByCottage(id);
        return list;
    }

    @RequestMapping(value="/getAllByBoat/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public List<AppointmentDTO> getAllByBoat(@PathVariable Long id) {
        List<AppointmentDTO> list = this.appointmentService.getAllByBoat(id);
        return list;
    }

    @DeleteMapping(value="/deleteAppointment/{id}")
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER') || hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<Long> deleteAppointment(@PathVariable Long id) {
        return this.appointmentService.deleteAppointment(id);
    }

    @PostMapping(value="/editAvailability")
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public Appointment editAvailability(@RequestBody AvailabilityDTO dto) {
        return this.appointmentService.editAvailability(dto);
    }

    @PostMapping(value="/editAvailabilityBoat")
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<Appointment> editAvailabilityBoat(@RequestBody AvailabilityDTO dto) {
        Appointment a = this.appointmentService.editAvailabilityBoat(dto);
        if(a == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @RequestMapping(value="/addNewAction", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_COTTAGE_OWNER')")
    public ResponseEntity<?> addNewAction(@RequestBody AddActionDTO dto) throws MessagingException {
        Appointment added = this.appointmentService.addNewAction(dto);
        if(added == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(added,HttpStatus.OK);
    }


    @RequestMapping(value="/addNewActionBoat", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_BOAT_OWNER')")
    public ResponseEntity<?> addNewActionBoat(@RequestBody AddActionDTO dto) throws MessagingException {
        Appointment added = this.appointmentService.addNewActionBoat(dto);
        if(added == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(added,HttpStatus.OK);
    }

}

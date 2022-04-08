package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.AddCottageDTO;
import com.example.WishAndFish.dto.AdditionalServicesDTO;
import com.example.WishAndFish.dto.AppointmentDTO;
import com.example.WishAndFish.dto.AvailabilityDTO;
import com.example.WishAndFish.model.Appointment;
import com.example.WishAndFish.model.Cottage;
import com.example.WishAndFish.service.AdditionalServiceService;
import com.example.WishAndFish.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/appointments")
@CrossOrigin()
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value="/getAllByCottage/{id}", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public List<AppointmentDTO> getAllByCottage(@PathVariable Long id) {
        return this.appointmentService.getAllByCottage(id);
    }

    @DeleteMapping(value="/deleteAppointment/{id}")
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<Long> deleteAppointment(@PathVariable Long id) {
        return this.appointmentService.deleteAppointment(id);
    }

    @PostMapping(value="/editAvailability")
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public Appointment editAvailability(@RequestBody AvailabilityDTO dto) {
        return this.appointmentService.editAvailability(dto);
    }

}

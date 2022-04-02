package com.example.WishAndFish.controller;

import com.example.WishAndFish.dto.AdditionalServicesDTO;
import com.example.WishAndFish.dto.AppointmentDTO;
import com.example.WishAndFish.model.Appointment;
import com.example.WishAndFish.service.AdditionalServiceService;
import com.example.WishAndFish.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/appointments")
@CrossOrigin()
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value="/getAllByCottage/{id}", method = RequestMethod.GET)
    public List<AppointmentDTO> getAllByCottage(@PathVariable Long id) {
        return this.appointmentService.getAllByCottage(id);
    }
}

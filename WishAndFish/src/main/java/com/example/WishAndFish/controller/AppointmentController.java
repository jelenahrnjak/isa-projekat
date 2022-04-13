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

    @RequestMapping(value="/addNewAction", method = RequestMethod.POST)
    //@PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<?> addNewAction(@RequestBody AddActionDTO dto) throws MessagingException {
        System.out.println("ISPISUJEM PODATKE:" + dto);
        Appointment added = this.appointmentService.addNewAction(dto);
        if(added == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(added,HttpStatus.OK);
}

}

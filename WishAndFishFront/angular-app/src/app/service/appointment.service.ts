import { Injectable } from '@angular/core';
import { ApiService, ConfigService } from '.';
import {map} from 'rxjs/operators';   
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(private apiService: ApiService,
    private config: ConfigService) { }


    findAppointments(id) {
      return this.apiService.get(this.config.appointment_url + `/getAllByCottage/${id}`, id)
      .pipe(map(appointments => {
        console.log(appointments)
        return appointments;
      }));   
    }

    findAppointmentsBoat(id) {
      return this.apiService.get(this.config.appointment_url + `/getAllByBoat/${id}`, id)
      .pipe(map(appointments => {
        console.log(appointments)
        return appointments;
      }));   
    }

    deleteAppointment(id){
      return this.apiService.delete(this.config.appointment_url + `/deleteAppointment/${id}`, id)
    .pipe(map(() => {
      console.log('Deleting appointment success');
    }));   
  }

  editAvailability(dto){
    return this.apiService.post(this.config.appointment_url + `/editAvailability`, dto)
    .pipe(map((appointment) => {
      console.log('Creating appointment success:');
    }));  
  }

  editAvailabilityBoat(dto){
    return this.apiService.post(this.config.appointment_url + `/editAvailabilityBoat`, dto)
    .pipe(map((appointment) => {
      console.log('Creating appointment success:');
    }));  
  }

  addNewAction(dto){
    console.log("evo me ")
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    return this.apiService.post(this.config.appointment_url + `/addNewAction`, JSON.stringify(dto), loginHeaders)
    .pipe(map(() => {
      console.log('Adding action success');
    }));
  }

  addNewActionBoat(dto){
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    return this.apiService.post(this.config.appointment_url + `/addNewActionBoat`, JSON.stringify(dto), loginHeaders)
    .pipe(map(() => {
      console.log('Adding action success');
    }));
  }
}

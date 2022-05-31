import { Injectable } from '@angular/core';
import { ApiService, ConfigService } from '.';
import { map, catchError } from 'rxjs/operators';   
import { HttpHeaders } from '@angular/common/http';
import { Appointment } from '../model/appointment';
import Swal from 'sweetalert2';
import { throwError } from 'rxjs';

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
    }))
    .pipe(catchError(error => this.checkError(error)));
  
  }
 
  editAvailabilityBoat(dto){
    return this.apiService.post(this.config.appointment_url + `/editAvailabilityBoat`, dto)
    .pipe(map((appointment) => {
      console.log('Creating appointment success:');
    }))
    .pipe(catchError(error => this.checkError(error)));
 
  }

  private checkError(error: any): any {
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: "There is a reservation in the selected period (or it is already free), you can't select it!" //error.error.message,
    })
    return throwError(error);
  }

  addNewAction(dto){
    console.log(dto)
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    return this.apiService.post(this.config.appointment_url + `/addNewAction`, JSON.stringify(dto), loginHeaders)
    .pipe(map(() => {
      console.log('Adding action success');
    }))
    .pipe(catchError(error => this.checkError(error)));

  }

  addNewActionBoat(dto){
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    return this.apiService.post(this.config.appointment_url + `/addNewActionBoat`, JSON.stringify(dto), loginHeaders)
    .pipe(map(() => {
      console.log('Adding action success');
    }))
    .pipe(catchError(error => this.freePeriod(error)));

  }


  private freePeriod(error: any): any {
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: "There is no free period in selected dates" //error.error.message,
    })
    return throwError(error);
  }

  getActions() {

    var email = localStorage.getItem('user');
    return this.apiService.get(this.config.appointment_url + `/getActions/` + email) 
  
  }
}

import { Injectable } from '@angular/core';
import { ApiService, ConfigService } from '.';
import {map} from 'rxjs/operators';   

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(private apiService: ApiService,
    private config: ConfigService) { }


    findAppointments(id) {
      return this.apiService.get(this.config.appointment_url + `/getAllByCottage/${id}`, id)
      .pipe(map(appointments => {
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
}

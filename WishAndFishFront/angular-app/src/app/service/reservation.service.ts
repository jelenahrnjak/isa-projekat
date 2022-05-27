import { PortalModule } from '@angular/cdk/portal';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private apiService: ApiService,
    private config: ConfigService) { }

    getAllByCottage(id) {
      return this.apiService.get(this.config.reservation_url + `/getAllByCottage/${id}`, id)
      .pipe(map(reservations => {
        return reservations;
      }));   
    }

    getAllByBoat(id) {
      return this.apiService.get(this.config.reservation_url + `/getAllByBoat/${id}`, id)
      .pipe(map(reservations => {
        return reservations;
      }));   
    }

    getNumberofReservationMonthlyCottage(id, year) {
      var dto = {
        "id": id,
        "year": year
      }
      console.log(dto)
      return this.apiService.post(this.config.reservation_url + `/getNumberofReservationMonthlyCottage`, dto)
      .pipe(map(reservations => {
        return reservations;
      }));   
    }

    getNumberofReservationYearlyCottage(id) {
      return this.apiService.get(this.config.reservation_url + `/getNumberofReservationYearlyCottage/${id}`, id)
      .pipe(map(reservations => {
        return reservations;
      }));   
    }

    search(data:any){ 
      console.log(data)
      return this.apiService.get(this.config.reservation_url + `/search`, data)
      .pipe(map(reservations => {   
        return reservations;
      }));
    }

    searchCottage(data:any){ 
      console.log(data)
      return this.apiService.get(this.config.reservation_url + `/searchCottage`, data)
      .pipe(map(reservations => {   
        return reservations;
      }));
    }
}

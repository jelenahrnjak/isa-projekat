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
}

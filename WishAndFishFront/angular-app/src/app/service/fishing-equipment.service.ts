import { map } from 'rxjs/operators';
import { ApiService } from './api.service';
import { ConfigService } from '.';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FishingEquipmentService {

  constructor(private apiService: ApiService,
    private config: ConfigService) { }


    findNavigation(id) {
      return this.apiService.get(this.config.fishing_equipment_url + `/getAllByBoat/${id}`, id)
      .pipe(map(navigations => {
        return navigations;
      }));   
    }
}

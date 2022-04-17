import { map } from 'rxjs/operators';
import { ConfigService } from './config.service';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavigationEquipmentService {

  constructor(private apiService: ApiService,
    private config: ConfigService) { }

    findNavigation(id) {
      return this.apiService.get(this.config.navigation_equipment_url + `/getAllByBoat/${id}`, id)
      .pipe(map(navigations => {
        return navigations;
      }));   
    }

      
   addEquipment(equipmentDTO){
     console.log(equipmentDTO)
    return this.apiService.post(this.config.navigation_equipment_url + `/addNavigationEquipment`, equipmentDTO)
    .pipe(map(path => {
      console.log('Adding equipment success: ' + path);
    }));;
  }
}

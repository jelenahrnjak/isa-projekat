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

    addEquipment(equipmentDTO){
      console.log(equipmentDTO)
     return this.apiService.post(this.config.fishing_equipment_url + `/addFishingEquipment`, equipmentDTO)
     .pipe(map(path => {
       console.log('Adding equipment success: ' + path);
     }));;
   }

   
  
   deleteFishingEquipment(id){
    return this.apiService.delete(this.config.fishing_equipment_url + `/deleteFishingEquipment/${id}`, id)
  .pipe(map(() => {
    console.log('Deleting FishingEquipment success');
  }));   
}
}

import { Injectable } from '@angular/core';
import { ApiService, ConfigService } from '.';
import {map} from 'rxjs/operators';   

@Injectable({
  providedIn: 'root'
})
export class AdditionalServicesService {

  constructor(private apiService: ApiService,
    private config: ConfigService) { }

    findAdditionalServices(id) {
      return this.apiService.get(this.config.additional_services_url + `/getAllByCottage/${id}`, id)
      .pipe(map(services => {
        return services;
      }));   
    }


    deleteAdditionalService(id){
      return this.apiService.delete(this.config.additional_services_url + `/deleteAdditionalServices/${id}`, id)
    .pipe(map(() => {
      console.log('Deleting additional service success');
    }));   
  }
    
}

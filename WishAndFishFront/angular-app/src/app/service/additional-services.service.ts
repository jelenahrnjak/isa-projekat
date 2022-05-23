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

    findAdditionalServicesBoat(id) {
      return this.apiService.get(this.config.additional_services_url + `/getAllByBoat/${id}`, id)
      .pipe(map(services => {
        return services;
      }));   
    }

    findAdditionalServicesAdventure(id) {
      return this.apiService.get(this.config.additional_services_url + `/getAllByAdventure/${id}`, id)
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


  public addService(serviceDto){
    return this.apiService.post(this.config.additional_services_url + `/addAdditionalService`, serviceDto)
    .pipe(map(path => {
      console.log('Adding additional service success: ' + path);
    }));;
  }

  
  public addServiceBoat(serviceDto){
    return this.apiService.post(this.config.additional_services_url + `/addAdditionalServiceBoat`, serviceDto)
    .pipe(map(path => {
      console.log('Adding additional service success: ' + path);
    }));;
  }

  findAdditionalServicesForAppointment(id) {
    return this.apiService.get(this.config.additional_services_url + `/findAdditionalServicesForAppointment/${id}`, id)
    .pipe(map(services => {
      console.log(services)
      return services;
    }));   
  }
    
}

import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {map} from 'rxjs/operators';   
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor( 
    private apiService: ApiService,
    private config: ConfigService) { }

  subscribeToCottage(cottageId: number, userEmail: string) {
  const data = {
    'cottageId': cottageId,
    'userEmail': userEmail
  };
 
  return this.apiService.put(this.config.client_url + `/subscribeToCottage`, JSON.stringify(data)) ;
  }

  subscribeToBoat(boatId: any, userEmail: string) {
    const data = {
      'boatId': boatId,
      'userEmail': userEmail
    };
   
    return this.apiService.put(this.config.client_url + `/subscribeToBoat`, data) ;
  }

  
  subscribeToAdventure(adventureId: any, userEmail: string) {
    const data = { 
      adventureId: adventureId,
      userEmail : userEmail
    };
   
    return this.apiService.put(this.config.client_url + `/subscribeToAdventure`, data) ;
  }
  

  checkSubscription(id: any, type : string) {
    
    return this.apiService.get(this.config.client_url + `/checkSubscription/${type}/${id}` , {
      headers: {
        "Access-Control-Allow-Origin": this.config.client_url,
        Authorization: "Bearer " + localStorage.refreshToken 
      },}) 
  
    }
}

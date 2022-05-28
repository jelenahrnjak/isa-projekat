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

  unsubscribeFromCottage(cottageId: number, userEmail: string) {
    const data = {
      'cottageId': cottageId,
      'userEmail': userEmail
    };
   
    return this.apiService.put(this.config.client_url + `/unsubscribeFromCottage`, JSON.stringify(data)) ;
  }

  subscribeToBoat(boatId: any, userEmail: string) {
    const data = {
      'boatId': boatId,
      'userEmail': userEmail
    };
   
    return this.apiService.put(this.config.client_url + `/subscribeToBoat`, JSON.stringify(data)) ;
  }

  unsubscribeFromBoat(boatId: any, userEmail: string) {
    const data = {
      'boatId': boatId,
      'userEmail': userEmail
    };
   
    return this.apiService.put(this.config.client_url + `/unsubscribeFromBoat`, JSON.stringify(data)) ;
  }
  
  subscribeToAdventure(adventureId: any, userEmail: string) {
    const data = { 
      adventureId: adventureId,
      userEmail : userEmail
    };
   
    return this.apiService.put(this.config.client_url + `/subscribeToAdventure`, JSON.stringify(data)) ;
  }
  
  unsubscribeFromAdventure(adventureId: any, userEmail: string) {
    const data = { 
      adventureId: adventureId,
      userEmail : userEmail
    };
   
    return this.apiService.put(this.config.client_url + `/unsubscribeFromAdventure`, JSON.stringify(data)) ;
  }

  checkSubscription(id: any, type : string) {
    var email = localStorage.getItem('user');
    return this.apiService.get(this.config.client_url + `/checkSubscription/${type}/${id}/${email}`) 
  }

  getCottageSubscriptions() {

    var email = localStorage.getItem('user');
    return this.apiService.get(this.config.client_url + `/cottageSubscriptions/${email}`) 
  
  }

  getBoatSubscriptions() {

    var email = localStorage.getItem('user');
    return this.apiService.get(this.config.client_url + `/boatSubscriptions/${email}`) 
  
  }

  getAdventureSubscriptions() {

    var email = localStorage.getItem('user');
    return this.apiService.get(this.config.client_url + `/adventureSubscriptions/${email}`) 
  
  }
 
}

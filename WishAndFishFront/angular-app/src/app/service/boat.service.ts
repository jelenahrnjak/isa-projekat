import { Boat } from './../model/boat.model';
import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {map} from 'rxjs/operators';  

@Injectable({
  providedIn: 'root'
})
export class BoatService {
 
  constructor(
    private apiService: ApiService,
    private config: ConfigService) { }

  getAll() {
    return this.apiService.get(this.config.boat_url)
      .pipe(map(boats => { 
        return boats;
      }));
  } 

  getAllClient() {
    return this.apiService.get(this.config.boat_url + `/client/${localStorage.getItem('user')}`)
      .pipe(map(boats => { 
        return boats;
      }));
  } 
  
  search(data:any){
  
    return this.apiService.get(this.config.boat_url + `/search`, data)
      .pipe(map(boats => {   
        return boats;
      }));
  } 
  
  searchClient(data:any){
  
    return this.apiService.get(this.config.boat_url + `/search/client/${localStorage.getItem('user')}`, data)
      .pipe(map(boats => {   
        return boats;
      }));
  }

  searchAppointments(data) {

    return this.apiService.get(this.config.boat_url + `/searchAppointments` , data )
    .pipe(map(boats => {   
      return boats;
    }));
  }

  addBoat(boat) {
    console.log(boat)
    return this.apiService.post(this.config.boat_url + `/addBoat`, boat)
    .pipe(map(() => {
      console.log('Adding boat success');
    }));
  }


  deleteBoat(id) {
    return this.apiService.delete(this.config.boat_url + `/deleteBoat/${id}`, id)
    .pipe(map(() => {
      console.log('Deleting boat success');
    }));   
  }

  findBoat(id) {
    console.log(localStorage.getItem("role"))
    return this.apiService.get(this.config.boat_url+ `/findBoat/${id}`, id)
    .pipe(map(cottage => {
      return cottage;
    }));   
  }

  editBasicInfo(boat) {
    return this.apiService.put(this.config.boat_url + `/editBasicInfo`, boat)
    .pipe(map(c => {
      console.log('Editing boat success');
    }));
  }


  
  getAllCommentsBoat(id) {
    return this.apiService.get(this.config.boat_url + `/getAllCommentsBoat/${id}`, id)
      .pipe(map(comments => { 
        return comments;
      }));
  } 
}

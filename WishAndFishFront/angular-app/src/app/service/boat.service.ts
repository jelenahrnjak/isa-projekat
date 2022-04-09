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
      return this.apiService.get(this.config.boat_url + '/client', {
        headers: {
          "Access-Control-Allow-Origin": this.config.client_url,
          Authorization: "Bearer " + localStorage.refreshToken 
        },})
        .pipe(map(boats => { 
          return boats;
        }));
    } 
    
    search(data:any){
    
      return this.apiService.get(this.config.boat_url + `/search`)
        .pipe(map(boats => {   
          return boats;
        }));
    }

    
    searchClient(data:any){
    
      return this.apiService.get(this.config.boat_url + `/search/client`,{
        headers: {
          "data" : data,
          "Access-Control-Allow-Origin": this.config.client_url,
          Authorization: "Bearer " + localStorage.refreshToken 
        },})
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
}

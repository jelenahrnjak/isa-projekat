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
    
    search(data:any){
    
      return this.apiService.get(this.config.boat_url + `/search`,data)
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

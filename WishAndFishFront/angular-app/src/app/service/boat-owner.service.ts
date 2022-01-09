import { Injectable } from '@angular/core';
import { ApiService, ConfigService } from '.';
import {map} from 'rxjs/operators'; 

@Injectable({
  providedIn: 'root'
})
export class BoatOwnerService {

  constructor(private apiService: ApiService,
    private config: ConfigService) { }

  
  getBoatsFromOwner() {
    return this.apiService.get(this.config.boat_owner_url + `/getBoatsFromOwner/${localStorage.getItem('user')}`)
      .pipe(map(boats => {
        return boats;
      }));
  } 
}

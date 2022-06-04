import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {map} from 'rxjs/operators';  

@Injectable({
  providedIn: 'root'
})
export class CottageOwnerService {

  constructor(private apiService: ApiService,
    private config: ConfigService) { }


  getCottagesFromOwner() {
    console.log("evo me ")
    return this.apiService.get(this.config.cottage_owner_url + `/getCottagesFromOwner/${localStorage.getItem('user')}`)
      .pipe(map(cottages => {
        return cottages;
      }));
  } 
}

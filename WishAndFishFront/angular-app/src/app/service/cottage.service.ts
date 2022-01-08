import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {map} from 'rxjs/operators';  

@Injectable({
  providedIn: 'root'
})
export class CottageService {

  cottages: any = [];
  logo:string="./assets/cottage1.jpg"

  constructor( private apiService: ApiService,
    private config: ConfigService) { }

  getAll() {
    return this.apiService.get(this.config.cottage_url)
      .pipe(map(cottages => {
        this.cottages = cottages;
        return this.cottages;
      }));
  } 

  
  getCottagesFromOwner() {
    return this.apiService.get(this.config.cottage_owner_url + `/getCottagesFromOwner/${localStorage.getItem('user')}`)
      .pipe(map(cottages => {
        return cottages;
      }));
  } 
}

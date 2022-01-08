import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {map} from 'rxjs/operators';   

@Injectable({
  providedIn: 'root'
})
export class CottageService {

  cottages: any = []; 
   
  constructor( private apiService: ApiService,
    private config: ConfigService) { }
 
  getAll() {
    return this.apiService.get(this.config.cottage_url)
      .pipe(map(cottages => {
        this.cottages = cottages;
        return this.cottages;
      }));
  } 

  search(data:any){
    
    return this.apiService.get(this.config.cottage_url + `/search`,data)
      .pipe(map(cottages => {  
        this.cottages = cottages;
        return this.cottages;
      }));
  }
}

import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {map} from 'rxjs/operators';   
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdventureService {

  constructor(private apiService: ApiService,
    private config: ConfigService) { }

  getAll() {
    return this.apiService.get(this.config.adventure_url)
      .pipe(map(adventures => { 
        return adventures;
      }));
  } 
  
  getAllClient() {
    return this.apiService.get(this.config.adventure_url + `/client/${localStorage.getItem('user')}`)
    .pipe(map(adventures => { 
        return adventures;
      }));
  } 

  search(data:any){ 
    return this.apiService.get(this.config.adventure_url + `/search`,data)
    .pipe(map(adventures => { 
      return adventures;
    }));
  }

  searchClient(data:any){ 
    return this.apiService.get(this.config.adventure_url + `/search/client/${localStorage.getItem('user')}`, data)
    .pipe(map(adventures => { 
      return adventures;
    }));
  }
}

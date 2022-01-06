import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import {map} from 'rxjs/operators';
import { Observable } from 'rxjs';
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  currentUser: any = null;

  constructor(
    private apiService: ApiService,
    private config: ConfigService
  ) {
  }


  getAll() {
    return this.apiService.get(this.config.users_url);
  }

  getUser() {
    return this.apiService.get(this.config.user_url + `/getOne`)
      .pipe(map(user => {
        this.currentUser = user;
        return user;
      }));
  } 

  update(data : any) : Observable<any> {
    return this.apiService.put(this.config.user_url + `/update`, data)
  }

  changePassword(data : any,) : Observable<any> {
    return this.apiService.put(this.config.user_url + `/changePassword`, data)
  }

  
 
}

import { Injectable } from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import { map, catchError } from 'rxjs/operators';   
import { HttpHeaders, HttpParams } from '@angular/common/http';
import Swal from 'sweetalert2';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CottageService {

  constructor( 
    private apiService: ApiService,
    private config: ConfigService) { }
 
  getAll() {
    return this.apiService.get(this.config.cottage_url)
      .pipe(map(cottages => { 
        return cottages;
      }));
  } 

  getAllClient() {
    return this.apiService.get(this.config.cottage_url + `/client/${localStorage.getItem('user')}`)
      .pipe(map(cottages => { 
        return cottages;
      }));
  } 

  search(data:any){
    
    return this.apiService.get(this.config.cottage_url + `/search`,data)
      .pipe(map(cottages => {   
        return cottages;
      }));
  }

  searchClient(data: { name: string; address: string; rating: string; price: string; }) { 

    return this.apiService.get(this.config.cottage_url + `/search/client/${localStorage.getItem('user')}` , data)
    .pipe(map(cottages => {   
      return cottages;
    }));

  }  
  
  searchAppointments(data) {

    return this.apiService.get(this.config.cottage_url + `/searchAppointments/` + localStorage.getItem('user') , data )
    .pipe(map(cottages => {   
      return cottages;
    }));

  }

  addCottage(cottage) {
    return this.apiService.post(this.config.cottage_url + `/addCottage`, cottage)
    .pipe(map(() => {
      console.log('Adding cottage success');
    }));
  }

  deleteCottage(id) {
    return this.apiService.delete(this.config.cottage_url + `/deleteCottage/${id}`, id)
    .pipe(map(() => {
      console.log('Deleting cottage success');
    }));   
  }

 findCottage(id) {
    return this.apiService.get(this.config.cottage_url + `/findCottage/${id}`, id)
    .pipe(map(cottage => {
      return cottage;
    }));   
  }

  editBasicInfo(cottage) {
    return this.apiService.put(this.config.cottage_url + `/editBasicInfo`, cottage)
    .pipe(map(c => {
      console.log('Editing cottage success');
    }))
    .pipe(catchError(error => this.edit(error)));
    ;
  }

  private edit(error: any): any {
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: "Cottage is reserved! Yoou can't edit it!" //error.error.message,
    })
    return throwError(error);
  }

  getAllCommentsCottage(id) {
    return this.apiService.get(this.config.cottage_url + `/getAllComments/${id}`, id)
      .pipe(map(comments => { 
        return comments;
      }));
  } 
 
}

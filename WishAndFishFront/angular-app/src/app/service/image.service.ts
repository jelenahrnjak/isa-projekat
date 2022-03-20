import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiService, ConfigService } from '.';
import {map} from 'rxjs/operators';   

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private _http:HttpClient, private apiService: ApiService,
    private config: ConfigService) { }

  public addImage(imageDto){
    return this.apiService.post(this.config.image_url + `/addImage`, imageDto)
    .pipe(map(path => {
      console.log('Adding cottage image success: ' + path);
    }));;
  }
}

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

  public addImageBoat(imageDto){
    return this.apiService.post(this.config.image_url + `/addImageBoat`, imageDto)
    .pipe(map(path => {
      console.log('Adding cottage image success: ' + path);
    }));;
  }

  public addCoverImageBoat(imageDto){
    console.log(imageDto)
    return this.apiService.post(this.config.image_url + `/addCoverImageBoat`, imageDto)
    .pipe(map(path => {
      console.log('Adding boat cover image success: ' + path);
    }));;
  }

  
  public addCoverImageCottage(imageDto){
    console.log(imageDto)
    return this.apiService.post(this.config.image_url + `/addCoverImageCottage`, imageDto)
    .pipe(map(path => {
      console.log('Adding cottage cover image success: ' + path);
    }));;
  }

  public deleteImage(path){
    return this.apiService.delete(this.config.image_url + `/deleteImage/${path}`, path)
    .pipe(map((path) => {
      console.log('Deleting image success' + path);
    }));  
  }


  findImages(id) {
    return this.apiService.get(this.config.image_url + `/getAllByCottage/${id}`, id)
    .pipe(map(images => {
      return images;
    })); 
  }


  findImagesBoat(id) {
    return this.apiService.get(this.config.image_url + `/getAllByBoat/${id}`, id)
    .pipe(map(images => {
      return images;
    })); 
  }
}

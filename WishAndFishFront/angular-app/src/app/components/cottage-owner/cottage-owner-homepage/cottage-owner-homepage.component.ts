import { ActivatedRoute } from '@angular/router';
import { ReservationService } from './../../../service/reservation.service';
import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../service/user.service';
import { DomSanitizer, SafeResourceUrl, } from '@angular/platform-browser';
import * as Chart from 'chart.js'
import { MapsAPILoader, MouseEvent } from '@agm/core';

@Component({
  selector: 'app-cottage-owner-homepage',
  templateUrl: './cottage-owner-homepage.component.html',
  styleUrls: ['./cottage-owner-homepage.component.css']
})
export class CottageOwnerHomepageComponent implements OnInit {
  google : any 
  title = 'My first AGM project';
  lat = 51.678418;
  lng = 7.809007;
  private geoCoder;
  address: string;
  zoom: number;

  constructor() {  }

  ngOnInit() {
    this.geoCoder = new google.maps.Geocoder();

  }


  markerDragEnd($event: MouseEvent) {
    console.log($event);
    console.log($event.coords.lat)  
    this.getAddress($event.coords.lat, $event.coords.lng);
  }

  getAddress(latitude, longitude) {
    this.geoCoder.geocode({ 'location': { lat: latitude, lng: longitude } }, (results, status) => {
      console.log(results);
      console.log(status);
      if (status === 'OK') {
        if (results[0]) {
          this.zoom = 12;
          this.address = results[0].formatted_address;
        } else {
          window.alert('No results found');
        }
      } else {
        window.alert('Geocoder failed due to: ' + status);
      }

    });
  }

  }



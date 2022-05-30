import { ActivatedRoute } from '@angular/router';
import { ReservationService } from './../../../service/reservation.service';
import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../service/user.service';
import { DomSanitizer, SafeResourceUrl, } from '@angular/platform-browser';
import * as Chart from 'chart.js'

@Component({
  selector: 'app-cottage-owner-homepage',
  templateUrl: './cottage-owner-homepage.component.html',
  styleUrls: ['./cottage-owner-homepage.component.css']
})
export class CottageOwnerHomepageComponent implements OnInit {
  title = 'My first AGM project';
  lat = 51.678418;
  lng = 7.809007;
  constructor() {  }

  ngOnInit() {
    
  }

  }



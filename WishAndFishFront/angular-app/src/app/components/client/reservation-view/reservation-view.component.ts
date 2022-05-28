import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators'; 
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs/Subject'; 
import { ClientService } from 'src/app/service/client.service';
import { ReservationService } from 'src/app/service/reservation.service';
import { Cottage } from 'src/app/model/cottage';
import { Boat } from 'src/app/model/boat.model';
import { FishingAdventure } from 'src/app/model/fishingAdventure.model';
import { BookingHistory } from '../../../model/booking-history.model';

@Component({
  selector: 'app-reservation-view',
  templateUrl: './reservation-view.component.html',
  styleUrls: ['./reservation-view.component.css']
})
export class ReservationViewComponent implements OnInit {

  constructor(  
    private route: ActivatedRoute,
    private clientService : ClientService,
    private reservationService : ReservationService,
    private router : Router) { }

  returnUrl: string; 
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  cottages: BookingHistory[] = [];  
  boats: BookingHistory[] = [];  
  adventures: BookingHistory[] = [];  
  
  showCottages = true;
  showBoats = true;
  showAdventures = true;

  ngOnInit() {
  
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/'; 

    this.reservationService.getBookingHistory().subscribe((data : BookingHistory[]) => {  
        for (var val of data) {  
          if(val.type == 'cottage'){ 
            this.cottages.push(val)
            
          }else if(val.type == 'boat'){
            this.boats.push(val)
          }else if(val.type == 'adventure'){ 
            this.adventures.push(val)
          }
          
        }
    }); 

  }

  viewCottages() {
    this.showCottages = !this.showCottages;
  }

  viewBoats() {
    this.showBoats = !this.showBoats;
  }

  viewAdventures() {
    this.showAdventures = !this.showAdventures;
  }

}

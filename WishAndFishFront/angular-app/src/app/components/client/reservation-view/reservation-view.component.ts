import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators'; 
import { FormControl,  Validators } from '@angular/forms';
import { Subject } from 'rxjs/Subject'; 
import { ClientService } from 'src/app/service/client.service';
import { ReservationService } from 'src/app/service/reservation.service';
import { Cottage } from 'src/app/model/cottage';
import { Boat } from 'src/app/model/boat.model';
import { FishingAdventure } from 'src/app/model/fishingAdventure.model';
import { BookingHistory } from '../../../model/booking-history.model';
import { Review } from '../../../model/review.model';
import Swal from 'sweetalert2';

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
  allData : BookingHistory[] = [];

  showCottages = true;
  showBoats = true;
  showAdventures = true;
  newReview : Review = new Review(0,false,0,"", localStorage.getItem('user'));
  currentReservation : BookingHistory; 
  owner : string = "";
  property : string = "";
  ownerCommented : boolean;
  propertyCommented : boolean;
  ownerComplaint : boolean;
  propertyComplaint : boolean;

  ctrl = new FormControl(null, Validators.required);

  toggle() {
    if (this.ctrl.disabled) {
      this.ctrl.enable();
    } else {
      this.ctrl.disable();
    }
  }

  ngOnInit() {
  
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/'; 

    this.resetHistory()

  }

  resetHistory(){
    this.allData = [];
    this.cottages = [];
    this.boats = [];
    this.adventures = [];

    this.reservationService.getBookingHistory().subscribe((data : BookingHistory[]) => {  
      this.allData = data;
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
  

  openReview(reservation){  
    this.newReview = new Review(reservation,null,0,"", localStorage.getItem('user')); 
    this.ctrl = new FormControl(null, Validators.required);
    
    for (var val of this.allData) { 
      if(val.id == reservation){
        this.owner = val.owner;
        this.property = val.name;
        this.ownerCommented = val.commentedOwner;
        this.propertyCommented = val.commentedEntity;
        this.ownerComplaint = val.complaintOwner;
        this.propertyComplaint = val.complaintEntity;
      }   
    }
    
  }  

  sendReview(){ 
 
    this.reservationService.addReview(this.newReview)
    .subscribe(
      result => { 
        Swal.fire({
          icon: 'success',
          title: 'Success!',
          text: 'Thank you for you feedback!',
        })   
 
        this.resetHistory();
      },
      error => {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Something went wrong. Please try again.',
        })  
 
        this.resetHistory();
      }
    );
  }

  sendComplaint(){ 
 
    this.reservationService.addComplaint(this.newReview)
    .subscribe(
      result => { 
        Swal.fire({
          icon: 'success',
          title: 'Success!',
          text: 'Thank you for you feedback!',
        })   
 
        this.resetHistory();
      },
      error => {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Something went wrong. Please try again.',
        })  
 
        this.resetHistory();
      }
    );
  }
 
  reservation(id){
    
  }
}

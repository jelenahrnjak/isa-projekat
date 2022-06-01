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
import Swal from 'sweetalert2'

@Component({
  selector: 'app-upcoming-reservations',
  templateUrl: './upcoming-reservations.component.html',
  styleUrls: ['./upcoming-reservations.component.css']
})
export class UpcomingReservationsComponent implements OnInit {

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
  all : BookingHistory[] = []
  
  showCottages = true;
  showBoats = true;
  showAdventures = true;

  name : string = "";
  totalPrice : number = 0; 
  from : string = "";
  to : string = ""; 
  currentId : number = 0;

  ngOnInit() {
  
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/'; 

    this.refreshData();

  }

  refreshData(){

    this.cottages = [];  
    this.boats = [];  
    this.adventures = [];  
    this.all = []
  
    
    this.reservationService.getUpcomingReservations().subscribe((data : BookingHistory[]) => {  

      this.all = data;
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

    this.currentId = 0;
    this.name = "";
    this.totalPrice = 0; 
    this.from = "";
    this.to = ""; 
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

  cancelReservation(id){

    for (var val of this.all) {  
      
      if(val.id == id){ 
        
        this.currentId = id;
        this.name = val.name;
        this.totalPrice = val.totalPrice 
        this.from = val.start;
        this.to = val.end; 
         
        return
      } 
      
    }

  }

  submit(){

    this.reservationService.cancelReservation(this.currentId)
    .subscribe(
      result => { 
        Swal.fire({
          icon: 'success',
          title: 'Success!',
          text: 'Successfully canceled!',
        })  
      },
      error => {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Something went wrong. Please try again.',
        })  
      },
      () => { 
        this.refreshData()
      }  
    )  

  }

}

import { Component, OnInit } from '@angular/core';
import { ClientService } from 'src/app/service/client.service';
import { AppointmentService } from 'src/app/service/appointment.service';
import { BookingHistory } from '../../../model/booking-history.model';
import { ReservationService } from '../../../service/reservation.service'  
import Swal from 'sweetalert2'

@Component({
  selector: 'app-action-view',
  templateUrl: './action-view.component.html',
  styleUrls: ['./action-view.component.css']
})
export class ActionViewComponent implements OnInit {
 
  penalties = 0;  
  cottages: BookingHistory[] = [];  
  boats: BookingHistory[] = [];  
  adventures: BookingHistory[] = [];  
  allData : BookingHistory[] = [];
  showCottages = true;
  showBoats = true;
  showAdventures = true;
  name : string = "";
  totalPrice : number = 0;
  beforePrice : number = 0;
  from : string = "";
  to : string = "";
  currentId : number = 0;

  constructor(
    private clientService : ClientService,
    private reservationService : ReservationService,
    private appointmentService : AppointmentService) { }

  ngOnInit() {

    this.clientService.getPenalties().subscribe((data : number) => {
      this.penalties = data
    }) 

    this.resetData()
  }

  resetData(){ 
    
    this.allData = [];
    this.cottages = [];
    this.boats = [];
    this.adventures = [];

    this.appointmentService.getActions().subscribe((data : BookingHistory[]) => {  
      this.allData = data;
      console.dir(this.allData)
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
    this.beforePrice = 0;
    this.from = "";
    this.to = "";
  }

  findCurrent(id){

    for (var val of this.allData) {  
      
      if(val.id == id){ 
        
        this.currentId = id;
        this.name = val.name;
        this.totalPrice = val.totalPrice
        this.beforePrice = val.beforePrice
        this.from = val.start;
        this.to = val.end;
        

        return
      } 
      
    }
    
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
  
  submit(){

    this.reservationService.bookAction(this.currentId)
      .subscribe(
        result => { 
          Swal.fire({
            icon: 'success',
            title: 'Success!',
            text: 'A confirmation email with details has been sent to your email address.',
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
          this.resetData()
        }  
      )
      
       
  }

}

import { Component, OnInit } from '@angular/core';
import { ClientService } from 'src/app/service/client.service';
import { AppointmentService } from 'src/app/service/appointment.service';
import { BookingHistory } from '../../../model/booking-history.model';

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
  current = 0

  constructor(
    private clientService : ClientService,
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
  }

  reservation(id){
    
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

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators'; 
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs/Subject';
import { CottageService } from 'src/app/service/cottage.service';
import { ClientService } from 'src/app/service/client.service';
import { Cottage } from 'src/app/model/cottage';
import { Boat } from 'src/app/model/boat.model';
import { FishingAdventure } from 'src/app/model/fishingAdventure.model';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css']
})
export class SubscriptionsComponent implements OnInit {

  returnUrl: string;
  notification: DisplayMessage; 
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  cottages: Cottage[] = [];  
  boats: Boat[] = [];  
  adventures: FishingAdventure[] = []; 
  
  showCottages = true;
  showBoats = true;
  showAdventures = true;

  constructor(  
    private route: ActivatedRoute,
    private clientService : ClientService,
    private router : Router) { }

  ngOnInit() {

     this.route.params
     .pipe(takeUntil(this.ngUnsubscribe))
     .subscribe((params: DisplayMessage) => {
       this.notification = params;
   }); 
    
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/'; 

    this.clientService.getCottageSubscriptions().subscribe((data : any) => {
      this.cottages = data; 
    }); 

    this.clientService.getBoatSubscriptions().subscribe((data : any) => {
      this.boats = data; 
    }); 

    this.clientService.getAdventureSubscriptions().subscribe((data : any) => {
      this.adventures = data; 
    }); 

  } 

  unsubscribeCottage(id){  
    
    this.clientService.unsubscribeFromCottage(id, localStorage.getItem('user')).subscribe(
      (data) => {  
        for(var v of this.cottages){
          if(v.id === id){
            v.show = false;
          }
        }
        alert("Successfully unsubscribed") 
        // this.ngOnInit()
      },
      (err) => {  
        alert('Already unsubscribed!') 
      }) 
  }

  unsubscribeBoat(id){  
    
    this.clientService.unsubscribeFromBoat(id, localStorage.getItem('user')).subscribe(
      (data) => {  
        for(var v of this.cottages){
          if(v.id === id){
            v.show = false;
          }
        }
        alert("Successfully unsubscribed") 
        // this.ngOnInit()
      },
      (err) => {  
        alert('Already unsubscribed!') 
      }) 
  }

  unsubscribeAdventure(id){  
    
    this.clientService.unsubscribeFromAdventure(id, localStorage.getItem('user')).subscribe(
      (data) => {  
        for(var v of this.cottages){
          if(v.id === id){
            v.show = false;
          }
        }
        alert("Successfully unsubscribed") 
        // this.ngOnInit()
      },
      (err) => {  
        alert('Already unsubscribed!') 
      }) 
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

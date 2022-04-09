import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router'; 
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BoatService } from 'src/app/service/boat.service';
import { Boat } from 'src/app/model/boat.model';
import { ClientService } from 'src/app/service/client.service';

@Component({
  selector: 'app-boats',
  templateUrl: './boats.component.html',
  styleUrls: ['./boats.component.css']
})
export class BoatsComponent implements OnInit {
  
  boats: Boat[] = [];  
  form: FormGroup;
  searchDTO = {
    "name" : "",
    "address" : "",
    "rating" : "",
    "price" : "", 
  };
  isClient : boolean = false;

  constructor(
    private route: ActivatedRoute,
    private boatService: BoatService,   
    private formBuilder: FormBuilder,
    private clientService: ClientService
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({  
      name: [''],
      address: [''],  
      rating: ['',Validators.compose([Validators.min(0), Validators.max(5), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])], 
      price: ['',Validators.compose([Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])],
      startDate : [''],
      endDate : [''],
      guests : [''] 
       
    })  
    this.form.reset();
    
    this.boatService.getAll().subscribe((data : any) => {
      for(var v of data){
        v.isSubscribed = this.checkSubscription(v.id)
        this.boats.push(v); 
        console.dir(this.checkSubscription(v.id))
      } 
    }); 

    if(localStorage.getItem('user')){
      this.isClient = true;
    } 
 
  }

  search(){
    this.searchDTO.name = this.form.get('name').value
    this.searchDTO.address = this.form.get('address').value
    this.searchDTO.rating = this.form.get('rating').value
    this.searchDTO.price = this.form.get('price').value
    this.boatService.search(this.searchDTO).subscribe((data : any) => { 
      this.boats = data; 
    }); 
  }

  clear(){
    this.form.reset();
    this.boatService.getAll().subscribe((data : any) => {
      this.boats = data;
    }); 
  }

  checkSubscription(boatId) : boolean { 
    this.clientService.checkSubscription(boatId, "boat").subscribe((data : any) => {
      return data;
    }); 

    return false;
  }
  
  details(){

  }

  subscribe(id){ 
    this.clientService.subscribeToBoat(id, localStorage.getItem('user')).subscribe(
      (data) => {  
        alert("Successfully subscribed") 
      },
      (err) => {  
        alert('Already subscribed!') 
      }) 
  }

}

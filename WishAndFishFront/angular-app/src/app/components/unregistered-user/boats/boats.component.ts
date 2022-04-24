import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; 
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BoatService } from 'src/app/service/boat.service';
import { Boat } from 'src/app/model/boat.model';
import { ClientService } from 'src/app/service/client.service';
import { clear } from 'console';

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
    private clientService: ClientService,
    private router: Router
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({  
      name: [''],
      address: [''],  
      rating: ['',Validators.compose([Validators.min(0), Validators.max(5), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])], 
      price: ['',Validators.compose([Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])],
      startDate : [''],
      endDate : [''],
      guests : [''],
      sorting : ['']
       
    })  

    this.clear();
    this.form.get('sorting').setValue(0)

    if(localStorage.getItem('user')){
      this.isClient = true;
    } 
 
  }

  search(){
    this.searchDTO.name = this.form.get('name').value
    this.searchDTO.address = this.form.get('address').value
    this.searchDTO.rating = this.form.get('rating').value
    this.searchDTO.price = this.form.get('price').value

    if(localStorage.getItem('role') === 'CLIENT'){ 
      this.boatService.searchClient(this.searchDTO).subscribe((data : any) => { 
      this.boats = data; 
    }); 
    }  else{ 
      this.boatService.search(this.searchDTO).subscribe((data : any) => { 
        this.boats = data; 
      }); 
    }
  }

  clear(){
    this.form.reset();
  
    this.form.get('sorting').setValue(0)
    if(localStorage.getItem('role') === 'CLIENT'){ 
      this.boatService.getAllClient().subscribe((data : any) => {
        this.boats = data;
      }); 
    }else{
      this.boatService.getAll().subscribe((data : any) => {
        this.boats = data;
      }); 

    }
  }  
  
  details(id){
    this.router.navigate(['/boat-details/'+id]);
  } 

  subscribe(id){  

    this.clientService.subscribeToBoat(id, localStorage.getItem('user')).subscribe(
      (data) => {  
        for(var v of this.boats){
          if(v.id === id){
            v.isSubscribed = true;
          }
        }
        alert("Successfully subscribed") 
      },
      (err) => {  
        alert('Already subscribed!') 
      }) 
  }

  unsubscribe(id){  

    this.clientService.unsubscribeFromBoat(id, localStorage.getItem('user')).subscribe(
      (data) => {  
        for(var v of this.boats){
          if(v.id === id){
            v.isSubscribed = false;
          }
        }
        alert("Successfully unsubscribed") 
      },
      (err) => {  
        alert('Already unsubscribed!') 
      }) 
  }

  changeSorting(){

    if(this.form.get('sorting').value == 1){ 

      this.boats.sort(function(a, b) { 
        return a.pricePerHour - b.pricePerHour;})

    }else{ 

      this.boats.sort(function(a, b) {

        return b.rating - a.rating  })
    }

  }

}

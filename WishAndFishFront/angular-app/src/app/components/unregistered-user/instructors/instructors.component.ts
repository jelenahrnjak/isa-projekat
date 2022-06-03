import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { AuthService, UserService } from '../../../service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs/Subject';
import { AdventureService } from 'src/app/service/adventure.service';
import { FishingAdventure } from 'src/app/model/fishingAdventure.model';
import { ClientService } from 'src/app/service/client.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-instructors',
  templateUrl: './instructors.component.html',
  styleUrls: ['./instructors.component.css']
})
export class InstructorsComponent implements OnInit {

  adventures: FishingAdventure[] = []
  form: FormGroup;
  searchDTO = {
    "name" : "",
    "address" : "",
    "rating" : "",
    "price" : "", 
    "instructor" : ""
  };
  isClient : boolean = false;

  constructor( 
    private adventureService: AdventureService,   
    private formBuilder: FormBuilder,
    private clientService : ClientService
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({  
      name: [''],
      address: [''], 
      instructor: [''],  
      rating: ['',Validators.compose([Validators.min(0), Validators.max(5), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])], 
      price: ['',Validators.compose([Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])],
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
    this.searchDTO.instructor = this.form.get('instructor').value

    if(localStorage.getItem('role') === 'ROLE_CLIENT'){ 
      this.adventureService.searchClient(this.searchDTO).subscribe((data : any) => { 
        this.adventures = data; 
      });
    }else{
      this.adventureService.search(this.searchDTO).subscribe((data : any) => { 
        this.adventures = data; 
      });
      
    }
  }

  clear(){
    this.form.reset();
    
    this.form.get('sorting').setValue(0)
    
    if(localStorage.getItem('role') === 'ROLE_CLIENT'){ 
      this.adventureService.getAllClient().subscribe((data : any) => {
        this.adventures = data;
      });
    } else{ 
      this.adventureService.getAll().subscribe((data : any) => {
        this.adventures = data;
      }); 
    }
  }
  
  details(){

  }

  subscribe(id){  

    this.clientService.subscribeToAdventure(id, localStorage.getItem('user')).subscribe(
      (data) => {  
        for(var v of this.adventures){
          if(v.id === id){
            v.isSubscribed = true;
          }
        }
          Swal.fire({
            icon: 'success',
            title: 'Yay!',
            text: 'Successfully subscribed!',
          })   
       },
       (err) => {  
          Swal.fire({
            icon: 'error',
            title: 'Ooops...',
            text: 'Already subscribed!',
          })  
       })  
  }

  unsubscribe(id){  

    this.clientService.unsubscribeFromAdventure(id, localStorage.getItem('user')).subscribe(
      (data) => {  
        for(var v of this.adventures){
          if(v.id === id){
            v.isSubscribed = false;
          }
        }
        Swal.fire({
          icon: 'success',
          title: 'Yay!',
          text: 'Successfully unsubscribed!',
        })   
       },
       (err) => {  
        Swal.fire({
          icon: 'error',
          title: 'Ooops...',
          text: 'Already unsubscribed!',
        })  
       })     
  }

  changeSorting(){

    if(this.form.get('sorting').value == 1){ 

      this.adventures.sort(function(a, b) { 
        return a.price - b.price;})

    }else{ 

      this.adventures.sort(function(a, b) {

        return b.rating - a.rating  })
    }

  }
}

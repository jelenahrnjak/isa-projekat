import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators'; 
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs/Subject';
import { CottageService } from 'src/app/service/cottage.service';
import { ClientService } from 'src/app/service/client.service';
import { Cottage } from 'src/app/model/cottage';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-cottage',
  templateUrl: './cottage.component.html',
  styleUrls: ['./cottage.component.css']
})
export class CottageComponent implements OnInit {
  returnUrl: string;
  notification: DisplayMessage; 
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  cottages: Cottage[] = [];  
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
    private cottageService: CottageService,   
    private formBuilder: FormBuilder,
    private clientService : ClientService,
    private router : Router) { }

  ngOnInit() {
    
    this.form = this.formBuilder.group({  
      name: [''],
      address: [''],  
      rating: ['',Validators.compose([Validators.min(0), Validators.max(5), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])], 
      price: ['',Validators.compose([Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])]  ,
      startDate : [''],
      endDate : [''],
      guests : [''] ,
      sorting : ['']
    })
     this.route.params
     .pipe(takeUntil(this.ngUnsubscribe))
     .subscribe((params: DisplayMessage) => {
       this.notification = params;
   }); 
    
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/'; 

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

    if(localStorage.getItem('role') === 'ROLE_CLIENT'){ 
      this.cottageService.searchClient(this.searchDTO).subscribe((data : any) => { 
        this.cottages = data; 
      });
    }else{
      this.cottageService.search(this.searchDTO).subscribe((data : any) => { 
        this.cottages = data; 
      });
    }
  }

  clear(){
    this.form.reset();
    
    this.form.get('sorting').setValue(0)
    this.cottages = []
    if(localStorage.getItem('role') === 'ROLE_CLIENT'){ 
      this.cottageService.getAllClient().subscribe((data : any) => {
        this.cottages = data;
        console.dir(data)
      }); 
    }else{
      this.cottageService.getAll().subscribe((data : any) => {
        this.cottages = data;
      }); }
  }
 
  details(id){
    this.router.navigate(['/cottage-details/'+id]);
  } 

  subscribe(id){ 
    this.clientService.subscribeToCottage(id, localStorage.getItem('user')).subscribe(
      (data) => {  
        for(var v of this.cottages){
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
    
    this.clientService.unsubscribeFromCottage(id, localStorage.getItem('user')).subscribe(
      (data) => {  
        for(var v of this.cottages){
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

      this.cottages.sort(function(a, b) { 
        return a.price - b.price;})

    }else{ 

      this.cottages.sort(function(a, b) {

        return b.rating - a.rating  })
    }

  }
}

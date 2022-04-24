import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { AuthService, UserService } from '../../../service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs/Subject';
import { CottageService } from 'src/app/service/cottage.service';
import { BoatService } from 'src/app/service/boat.service'; 
import { AdventureService } from 'src/app/service/adventure.service';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-appoitment-search',
  templateUrl: './appoitment-search.component.html',
  styleUrls: ['./appoitment-search.component.css']
})
export class AppoitmentSearchComponent implements OnInit {

  returnUrl: string;
  notification: DisplayMessage; 
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  items: any  
  form: FormGroup;
     
  constructor(  
    private route: ActivatedRoute,
    private cottageService: CottageService, 
    private boatService : BoatService,
    private adventureService: AdventureService,  
    private formBuilder: FormBuilder) { }

    selectedEntity = 0;   
    searchDTO = {
      "name" : "",
      "address" : "",
      "rating" : "",
      "price" : "", 
      "startDate" : new Date(),
      "endDate" : new Date(),
      "maxPersons" : 0,
      "startTime" : "",
      "hours" : 0
    }
 
    ngOnInit() {
    
      this.form = this.formBuilder.group({  
        name: [''],
        address: [''],  
        rating: ['',Validators.compose([Validators.min(0), Validators.max(5), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])], 
        price: ['',Validators.compose([Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])]  ,
        startDate : ['',Validators.compose([Validators.required])],
        endDate : ['',Validators.compose([Validators.required])],
        guests : [''],
        startTime : [''],
        hours : ['']
      })
       this.route.params
       .pipe(takeUntil(this.ngUnsubscribe))
       .subscribe((params: DisplayMessage) => {
         this.notification = params;
     }); 
     this.form.reset(); 
    }

    
    search(){ 
      if(this.selectedEntity == 1 && this.form.get('startDate').invalid && this.form.get('endDate').invalid){  
        alert('You must enter start and end date!')
        return 
      }

      this.searchDTO.name = this.form.get('name').value
      this.searchDTO.address = this.form.get('address').value
      this.searchDTO.rating = this.form.get('rating').value
      this.searchDTO.price = this.form.get('price').value 
      
      this.searchDTO.startDate = this.form.get('startDate').value; 
      this.searchDTO.maxPersons = this.form.get('guests').value; 

      if(this.selectedEntity == 1){ 
        this.searchDTO.endDate = this.form.get('endDate').value;
        this.cottageService.searchAppointments(this.searchDTO).subscribe((data : any) => {
          this.items = data;
        }); 
      }else if(this.selectedEntity == 2){
        this.searchDTO.startTime = this.form.get('startTime').value;
        this.searchDTO.hours = this.form.get('hours').value; 
        this.boatService.searchAppointments(this.searchDTO).subscribe((data : any) => {
          this.items = data;
        }); 

      }else if(this.selectedEntity == 3){ 
        this.adventureService.search(this.searchDTO).subscribe((data : any) => {
          this.items = data;
        }); 
    }}

    clear(){
      this.form.reset();
    }

    onChange(){ 
      
      if(this.selectedEntity == 1){ 
        this.cottageService.getAll().subscribe((data : any) => {
          this.items = data;
        }); 
      }else if(this.selectedEntity == 2){ 
        this.boatService.getAll().subscribe((data : any) => {
          this.items = data;
        }); 

      }else if(this.selectedEntity == 3){ 
        this.adventureService.getAll().subscribe((data : any) => {
          this.items = data;
        }); 
      }

    }

    details(id){

    }

}

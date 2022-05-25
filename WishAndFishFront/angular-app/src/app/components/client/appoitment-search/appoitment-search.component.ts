import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { AuthService, UserService } from '../../../service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs/Subject';
import { CottageService } from 'src/app/service/cottage.service';
import { BoatService } from 'src/app/service/boat.service'; 
import { AdventureService } from 'src/app/service/adventure.service'; 
import { AdditionalServicesService } from 'src/app/service/additional-services.service';
import { АdditionalService } from 'src/app/model/additional-service.model';
import { Appointment } from '../../../model/appointment';
import { AppointmentService } from '../../../service/appointment.service' 
import { error } from 'util';

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
    private additionalServicesService : AdditionalServicesService ,
    private formBuilder: FormBuilder,
    private appointmentService : AppointmentService) { }

    selectedEntity = 0;   
    searchDTO = {
      "name" : "",
      "address" : "",
      "rating" : "",
      "price" : "", 
      "startDate" : new Date(),
      "endDate" : new Date(),
      "maxPersons" : 0, 
    } 
    minDate: Date = new Date();
    minDateMax : Date = new Date(); 
    message = "Please select enetity and then choose criteria.";
    additionalServices : АdditionalService[] = [];
    totalPrice : number = 0;
    currentEntity : number = 0;

    ngOnInit() {
    
      this.form = this.formBuilder.group({  
        name: [''],
        address: [''],  
        rating: ['',Validators.compose([Validators.min(0), Validators.max(5), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])], 
        price: ['',Validators.compose([Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])]  ,
        startDate : ['',Validators.compose([Validators.required])],
        endDate : ['',Validators.compose([Validators.required])],
        guests : [''], 
        sorting : ['']
         
      })  

       this.route.params
       .pipe(takeUntil(this.ngUnsubscribe))
       .subscribe((params: DisplayMessage) => {
         this.notification = params;
     });  
      

     this.form.reset(); 
   
     this.form.get('sorting').setValue(0)
 
    }

    checkDates(){

      if(this.form.get('startDate').value >= this.form.get('endDate').value){
        this.form.get('endDate').setValue("")

      }

      var currenttimestamp = (new Date(this.form.get('startDate').value)).getTime(); 
      var onedayaftertimestamp=currenttimestamp+(86400000);//1 day=86400000 ms; 

      this.minDateMax = new Date(onedayaftertimestamp) 
    }

    search(){ 
      this.message = null; 
      if((this.form.get('startDate').invalid || this.form.get('endDate').invalid)){  

        this.message = 'You must enter start and end date!'
        return 
      }

      // if(this.selectedEntity != 1 && (this.form.get('startDate').invalid || this.form.get('startTime').invalid || this.form.get('hours').invalid)){  
      //   this.message =  'You must enter start date, start time and how many hours you want to stay!'
      //   return 
      // }  
      this.message = null

      this.searchDTO.name = this.form.get('name').value
      this.searchDTO.address = this.form.get('address').value
      this.searchDTO.rating = this.form.get('rating').value
      this.searchDTO.price = this.form.get('price').value 
      
      this.searchDTO.startDate = this.form.get('startDate').value; 
      this.searchDTO.endDate = this.form.get('endDate').value;
      this.searchDTO.maxPersons = this.form.get('guests').value; 

      if(this.selectedEntity == 1){ 
        this.cottageService.searchAppointments(this.searchDTO).subscribe((data : any) => {
          this.items = data;
          if(this.items.length == 0 ){
            this.message = "There are no apoitments for this criteria :("
          }
        }); 
      }else if(this.selectedEntity == 2){   
        this.boatService.searchAppointments(this.searchDTO).subscribe((data : any) => {
          this.items = data;
          if(this.items.length == 0 ){
            this.message = "There are no apoitments for this criteria :("
          }
        });  
      }else if(this.selectedEntity == 3){    
        this.adventureService.searchAppointments(this.searchDTO).subscribe((data : any) => {
          this.items = data;
          if(this.items.length == 0 ){
            this.message = "There are no apoitments for this criteria :("
          }
        }); 
    }
  }

    clear(){
      this.form.reset();
      
      this.form.get('sorting').setValue(0)
    }

    onChange(){ 
      this.message = null;
      this.form.reset() 
      this.form.get('sorting').setValue(0)
      this.items = []
    } 
    addServices(id, price){
      this.currentEntity = id

      var Time = new Date(this.searchDTO.endDate).getTime() - new Date(this.searchDTO.startDate).getTime(); 
      var Days = Time / (1000 * 3600 * 24); //Diference in Days

      this.totalPrice = price * Days

      if(this.selectedEntity == 1){     

        this.additionalServicesService.findAdditionalServices(id).subscribe((data : any) => {
          this.additionalServices = data;
        });
         
      }else if(this.selectedEntity == 2){ 
        this.additionalServicesService.findAdditionalServicesBoat(id).subscribe((data : any) => {
          this.additionalServices = data;
        }); 

      }else if(this.selectedEntity == 3){ 
        this.additionalServicesService.findAdditionalServicesAdventure(id).subscribe((data : any) => {
          this.additionalServices = data;
        });
      }
 
    }

    reserve(){
      this.additionalServices.forEach(i=>{
        if(i.isSelected){
          console.log(i.name)
        }
      })

      var startDate = this.searchDTO.startDate 
      var endDate = this.searchDTO.endDate  

      var appoitment = new Appointment(startDate, endDate, this.searchDTO.maxPersons, this.totalPrice, null, true, false, this.additionalServices, this.currentEntity, this.selectedEntity )
  
      this.appointmentService.addApointment(appoitment)
      .subscribe(
        result => { 
          alert('success')
        },
        error => {
          alert('error')
        },
        () => {
          // 'onCompleted' callback.
          // No errors, route to new page here
        }
      );

    } 

  toStartTime(timeString, date : string){
    var timeTokens = timeString.split(':');
    var dateTokens = date.split('-')
    return new Date(Number(dateTokens[0]) ,Number(dateTokens[1]) -1 ,Number(dateTokens[2]) , timeTokens[0], timeTokens[1], 0); 
    
  }

  toEndTime(timeString, date : string){
    var timeTokens = timeString.split(':');
    var dateTokens = date.split('-') 
    return new Date(Number(dateTokens[0]) ,Number(dateTokens[1]) -1 ,Number(dateTokens[2]), Number(timeTokens[0]) , timeTokens[1], 0);  
    
  }
     
    changedService(price : number, isSelected){
      
      var change = Number(price)
      if(isSelected){
        this.totalPrice = this.totalPrice + change
      }else{
        this.totalPrice = this.totalPrice - change
      }
 
    }

    changeSorting(){

      if(this.form.get('sorting').value == 1){ 
  
        this.items.sort(function(a, b) { 
          return a.price - b.price;})
  
      }else{ 
  
        this.items.sort(function(a, b) {
  
          return b.rating - a.rating  })
      }
  
    }

    changeSorting(){

      if(this.form.get('sorting').value == 1){ 
  
        this.items.sort(function(a, b) { 
          return a.price - b.price;})
  
      }else{ 
  
        this.items.sort(function(a, b) {
  
          return b.rating - a.rating  })
      }
  
    }

}

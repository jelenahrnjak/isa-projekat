import { AdditionalServicesService } from './../../../service/additional-services.service';
import { CottageService } from './../../../service/cottage.service';
import { АdditionalService } from 'src/app/model/additional-service.model';
import { FormGroup, FormBuilder } from '@angular/forms';
import { CommentService } from './../../../service/comment.service';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ReservationService } from './../../../service/reservation.service';
import { Component, OnInit } from '@angular/core';
import { formatDate, DatePipe } from '@angular/common';
import { Reservation } from 'src/app/model/reservation.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-cottage-reservation-history',
  templateUrl: './cottage-reservation-history.component.html',
  styleUrls: ['./cottage-reservation-history.component.css']
})
export class CottageReservationHistoryComponent implements OnInit {

  reservations: any
  id: any
  clientCame: boolean = true
  validComment: boolean = false
  comment= {
    "content": "",
    "client": "",
    "bad": false,
    "came": true,
    "reservationID": "",
    "approved": true
  }
  form: FormGroup;

  dto = {
    "criteria": "",
    "id": -1
  }

  client: string
  startDate: String | any;
  endDate: String | any;
  startTime: any;
  endTime: any;
  todayDate:Date = new Date();
  additionalServices : АdditionalService[] = [];
  totalPrice : number = 0;
  pipe = new DatePipe('en-US');
  pricePerDay: any


  constructor(private route: ActivatedRoute,
    private router: Router,
    private http : HttpClient,
    private reservationService: ReservationService,
    private formBuilder: FormBuilder,
    private commentService: CommentService,
    private additionalServicesService: AdditionalServicesService,
    private cottageService: CottageService
    ) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;
    this.startTime = "14:00"
    this.endTime = "12:00"
   

    this.reservationService.getAllByCottage(this.id).subscribe((data : any) => {
      this.reservations = data;
      console.log(this.reservations)
      });

      this.form = this.formBuilder.group({  
        name: [''],         
      }) 


    
      this.additionalServicesService.findAdditionalServices(this.id).subscribe((data : any) => {
        this.additionalServices = data;
        console.log(data)
      }); 

      this.cottageService.findCottage(this.id).subscribe((data) => {
        this.pricePerDay = data.pricePerDay
      })
  }

  came($event){
    if ($event.target.checked === true) {
      this.clientCame = false;
      this.comment.came = false;
      this.validComment = true
      this.comment.content = ""

    }
    else if ($event.target.checked === false) {
      this.clientCame = true;
      this.comment.came = true;
      if(this.comment.content !== ""){
        this.validComment = true;
      }else{
        this.validComment = false;
      }
    }
  }

  comments($event){
    console.log($event)
    if(this.comment.content !== ""){
      this.validComment = true;
    }
  }

  bad($event){
    if($event.target.checked === true){
        this.comment.bad = true
    }
    else if($event.target.checked === false){
      this.comment.bad = false
  }
  }

  addComment(){
    if(!this.clientCame){
      this.comment.content = "",
      this.comment.bad = false,
      this.comment.approved = false;
    }

    this.commentService.addComment(this.comment).subscribe(data => {
      this.comment= {
        "content": "",
        "client": "",
        "bad": false,
        "came": true,
        "reservationID": "",
        "approved": true
      } 
      this.clientCame = true 
      window.location.reload() 
    })

    console.log(this.clientCame)

  }

  close(){
    this.comment= {
      "content": "",
      "client": "",
      "bad": false,
      "came": true,
      "reservationID": "",
      "approved": true

    } 
    this.clientCame = true  
  }

  findClient(email, id){
    this.comment.client = email
    this.comment.reservationID = id

    this.client = email;

  }


  clear(){
    this.form.reset();
        
    this.reservationService.getAllByCottage(this.id).subscribe((data : any) => {
      this.reservations = data;
      console.log(this.reservations)
      });
  }

  search(){
    this.dto.criteria = this.form.get('name').value
    this.dto.id = this.id
    this.reservationService.searchCottage(this.dto).subscribe((data : any) => { 
      this.reservations = data; 
    });
  }


//reservations:///////////////////////////////////////////
changedService(price : number, isSelected){
      
  var change = Number(price)
  if(isSelected){
    this.totalPrice = this.totalPrice + change
  }else{
    this.totalPrice = this.totalPrice - change
  }

}


getSelectedAdditionalServices(){
  let arr : АdditionalService[] = [];

  for (var val of this.additionalServices) {
    if(val.isSelected){
      arr.push(val)
    }
  }

  return arr
}


reserve(){
  this.additionalServices.forEach(i=>{
    if(i.isSelected){
      console.log(i.name)
    }
  })

  if(this.startDate >= this.endDate){
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Start date is greater or equal then end date!',
    }) 
  }

  else if(this.startDate == undefined || this.endDate == undefined){
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Select dates!',
    }) 
  }

  else{
    this.startDate = this.pipe.transform(this.startDate, 'yyyy-MM-dd');
    this.endDate = this.pipe.transform(this.endDate, 'yyyy-MM-dd');

    var reservation = new Reservation(this.client,this.startDate,this.endDate,this.totalPrice, false, this.getSelectedAdditionalServices(),  1, this.id)
  
      
    this.reservationService.createReservation(reservation)
    .subscribe(
      result => { 
        Swal.fire({
          icon: 'success',
          title: 'Success!',
          text: 'A confirmation email with details has been sent to your email address.',
        }) 
        
        this.startDate = "",
        this.endDate = ""
        setTimeout(() => {window.location.reload()}, 2000); 
      },
      error => {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Something went wrong. Please try again.',
        })  
        this.startDate = "",
        this.endDate = "",
        setTimeout(() => {window.location.reload()}, 2000); 

      }
    );
  }
  
} 

calculatePrice($event){

  this.endDate = $event.target.value
  console.log(this.endDate)

  if(this.startDate !== undefined){
    this.startDate = new Date(this.pipe.transform(this.startDate, 'yyyy-MM-dd'));
    this.endDate = new Date(this.pipe.transform($event.target.value, 'yyyy-MM-dd'));
    console.log(this.endDate)

    var Time = this.endDate.getTime() - this.startDate.getTime(); 
    var Days = Time / (1000 * 3600 * 24); //Diference in Days

    this.totalPrice = this.pricePerDay * Days
  }
 

}

calculatePriceStart($event){

  this.startDate = $event.target.value;
  console.log(this.startDate)

  if(this.endDate !== undefined){
    this.startDate = new Date(this.pipe.transform(this.startDate, 'yyyy-MM-dd'));
    this.endDate = new Date(this.pipe.transform(this.endDate, 'yyyy-MM-dd'));
    console.log(this.endDate)

    var Time = this.endDate.getTime() - this.startDate.getTime(); 
    var Days = Time / (1000 * 3600 * 24); //Diference in Days

    this.totalPrice = this.pricePerDay * Days
  }
 

}




}

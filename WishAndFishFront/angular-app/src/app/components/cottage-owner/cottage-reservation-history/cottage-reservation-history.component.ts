import { FormGroup, FormBuilder } from '@angular/forms';
import { CommentService } from './../../../service/comment.service';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ReservationService } from './../../../service/reservation.service';
import { Component, OnInit } from '@angular/core';
import { formatDate } from '@angular/common';

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
    "came": true
  }
  form: FormGroup;

  dto = {
    "criteria": "",
    "id": -1
  }
  constructor(private route: ActivatedRoute,
    private router: Router,
    private http : HttpClient,
    private reservationService: ReservationService,
    private formBuilder: FormBuilder,
    private commentService: CommentService
    ) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;

   

    this.reservationService.getAllByCottage(this.id).subscribe((data : any) => {
      this.reservations = data;
      console.log(this.reservations)
      });

      this.form = this.formBuilder.group({  
        name: [''],         
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
      this.comment.content = ""
      this.comment.bad = false
    }

    this.commentService.addComment(this.comment).subscribe(data => {
      this.comment= {
        "content": "",
        "client": "",
        "bad": false,
        "came": true
      } 
      this.clientCame = true  
    })

    console.log(this.clientCame)

  }

  close(){
    this.comment= {
      "content": "",
      "client": "",
      "bad": false,
      "came": true
    } 
    this.clientCame = true  
  }

  findClient(email){
    this.comment.client = email
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

}

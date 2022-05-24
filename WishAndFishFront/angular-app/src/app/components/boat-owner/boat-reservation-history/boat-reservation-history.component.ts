import { FormGroup, FormBuilder } from '@angular/forms';
import { CommentService } from './../../../service/comment.service';
import { ReservationService } from './../../../service/reservation.service';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-boat-reservation-history',
  templateUrl: './boat-reservation-history.component.html',
  styleUrls: ['./boat-reservation-history.component.css']
})
export class BoatReservationHistoryComponent implements OnInit {
  reservations: any
  id: any
  clientCame: boolean = true
  validComment: boolean = false
  comment= {
    "content": "",
    "client": "",
    "bad": false,
    "came": true,
    "reservationID": ""
  }
  form: FormGroup;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private http : HttpClient,
    private reservationService: ReservationService,
    private formBuilder: FormBuilder,
    private commentService: CommentService
    ) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.reservationService.getAllByBoat(this.id).subscribe((data : any) => {
      this.reservations = data;
      console.log(this.reservations)
      });


      this.form = this.formBuilder.group({  
        name: [''],         
      }) 
  }
  clear(){
    this.form.reset();
        
    this.reservationService.getAllByBoat(this.id).subscribe((data : any) => {
      this.reservations = data;
      console.log(this.reservations)
      });
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
        "came": true,
        "reservationID": ""
      } 
      this.clientCame = true  
    })
    window.location.reload()
    console.log(this.clientCame)

  }

  close(){
    this.comment= {
      "content": "",
      "client": "",
      "bad": false,
      "came": true,
      "reservationID": ""
    } 
    this.clientCame = true  
  }

  findClient(email, id){
    this.comment.client = email
    this.comment.reservationID = id
  }

}

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

  constructor(private route: ActivatedRoute,
    private router: Router,
    private http : HttpClient,
    private reservationService: ReservationService,
    ) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;

   

    this.reservationService.getAllByCottage(this.id).subscribe((data : any) => {
      this.reservations = data;
      console.log(this.reservations)
      });
  }

}

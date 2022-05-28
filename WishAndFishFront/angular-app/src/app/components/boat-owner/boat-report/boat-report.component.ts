import { BoatService } from './../../../service/boat.service';
import { ActivatedRoute } from '@angular/router';
import { ReservationService } from './../../../service/reservation.service';
import { Component, OnInit } from '@angular/core';
import * as Chart from 'chart.js'
import Swal from 'sweetalert2';

@Component({
  selector: 'app-boat-report',
  templateUrl: './boat-report.component.html',
  styleUrls: ['./boat-report.component.css']
})
export class BoatReportComponent implements OnInit {
  userRole = localStorage.getItem('role');
  title = 'angular8chartjs';
  canvas: any;
  ctx: any;
  id;
  myChart: any;
  myChartWeek: any
  myChartIncome: any

  startDate: String | any;
  endDate: String | any;
  startDateIncome: String | any;
  endDateIncome: String | any;
  myVar : Record<string, number> = {

 }
 totalPerWeek = 0;
 rating = 0.0

  constructor(private reservationService: ReservationService,private route: ActivatedRoute, private boatService: BoatService) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.reservationService.getNumberofReservationYearlyBoat(this.id).subscribe((data : any) => {

      console.log(data)
    let keys = Object.keys(data)
    let values = Object.values(data)
    this.canvas = document.getElementById('myChartYear');
    this.ctx = this.canvas.getContext('2d');
    let myChartYear = new Chart(this.ctx, {
      type: 'bar',
      data: {
          labels: keys,
          datasets: [{
              label: '# of Reservations',
              data: values,
              backgroundColor: [
                  'rgba(255, 99, 132, 1)',
                  'rgba(54, 162, 235, 1)',
                  'rgba(54, 100, 235, 1)'
              ],
              borderWidth: 1,

          }],
      },

      options: {
        responsive: false,
        display:true,

      }
    });

    })
  
  //report per month
  this.reportPerMonth("2022");

    this.boatService.findBoat(this.id).subscribe((data) => {
      this.rating = data.rating
    })

  }


  reportPerMonth(year){
    this.reservationService.getNumberofReservationMonthlyBoat(this.id, year).subscribe((data : any) => {
     

    this.canvas = document.getElementById('myChart');
    this.ctx = this.canvas.getContext('2d');
    //this.myChart.destroy();
    this.myChart = new Chart(this.ctx, {
      type: 'bar',
      data: {
          labels: ["January", "February", "March", "April", "May", "Jun", "July", "August", "September", "October", "November", "December"],
          datasets: [{
              label: '# of Reservations',
              data: [data["JANUARY"] , data["FEBRUARY"],data["MARCH"], data["APRIL"], data["MAY"], data["JUN"], data["JULY"], data["AUGUST"], data["SEPTEMBER"], data["OCTOBER"], data["NOVEMBER"], data["DECEMBER"]],
              backgroundColor: [
                  'rgba(255, 99, 132, 1)',
                  'rgba(54, 162, 235, 1)',
                  'rgba(255, 206, 86, 1)',
                  'rgba(255, 145, 86, 1)',
                  'rgba(255, 104, 86, 1)',
                  'rgba(255, 125, 86, 1)',
                  'rgba(255, 23, 86, 1)',
                  'rgba(255, 206, 75, 1)',
                  'rgba(255, 206, 124, 1)',
                  'rgba(47, 206, 124, 1)',
                  'rgba(125, 206, 124, 1)',
                  'rgba(10, 206, 124, 1)',


              ],
              borderWidth: 1
          }]
      },
      options: {
        responsive: false,
        display:true
      }
    });

      });

    
  }


  selectYear($event){
    console.log($event.target.value)
    this.myChart.destroy();
    this.reportPerMonth($event.target.value);

  }

ifOwner(){
  if(this.userRole === 'ROLE_BOAT_OWNER'){
    return true;
  }
  return false;
 }
}
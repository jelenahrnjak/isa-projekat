import { ReservationService } from './../../../service/reservation.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import * as Chart from 'chart.js'

@Component({
  selector: 'app-cottage-report',
  templateUrl: './cottage-report.component.html',
  styleUrls: ['./cottage-report.component.css']
})
export class CottageReportComponent implements OnInit {

  constructor(private reservationService: ReservationService,private route: ActivatedRoute) { }
  title = 'angular8chartjs';
  canvas: any;
  ctx: any;
  id;
  myChart: any;

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;
    //report per year
    this.reservationService.getNumberofReservationYearlyCottage(this.id).subscribe((data : any) => {
    console.log(data)
    
    this.canvas = document.getElementById('myChartYear');
    this.ctx = this.canvas.getContext('2d');
    let myChartYear = new Chart(this.ctx, {
      type: 'bar',
      data: {
          labels: ["2021", "2022"],
          datasets: [{
              label: '# of Reservations',
              data: [data["2021"] , data["2022"]],
              backgroundColor: [
                  'rgba(255, 99, 132, 1)',
                  'rgba(54, 162, 235, 1)'
              ],
              borderWidth: 1
          }]
      },
      options: {
        responsive: false,
        display:true
      }
    });
    
    //report per month
    this.reportPerMonth("2022");
    });
  }

  reportPerMonth(year){
    this.reservationService.getNumberofReservationMonthlyCottage(this.id, year).subscribe((data : any) => {
      console.log(data)
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

}

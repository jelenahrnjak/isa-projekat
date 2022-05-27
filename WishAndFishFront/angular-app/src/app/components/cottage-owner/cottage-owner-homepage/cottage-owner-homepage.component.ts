import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../service/user.service';
import { DomSanitizer, SafeResourceUrl, } from '@angular/platform-browser';
import * as Chart from 'chart.js'

@Component({
  selector: 'app-cottage-owner-homepage',
  templateUrl: './cottage-owner-homepage.component.html',
  styleUrls: ['./cottage-owner-homepage.component.css']
})
export class CottageOwnerHomepageComponent implements OnInit {
  responsiveOptions;
  lat = 51.678418;
  lng = 7.809007;
  src
  title = 'angular8chartjs';
  canvas: any;
  ctx: any;
  constructor(private userService: UserService, public sanitizer: DomSanitizer) {  this.responsiveOptions = [{
    breakpoint: '1024px',
    numVisible: 1,
    numScroll: 3
}];}

  ngOnInit() {
    
    this.src = "https://maps.google.com/maps?q=" + this.lat + "," + this.lng +"&t=&z=13&ie=UTF8&iwloc=&output=embed"
    this.src = this.sanitizer.bypassSecurityTrustResourceUrl(this.src);

  }

  ngAfterViewInit() {
    this.canvas = document.getElementById('myChart');
    this.ctx = this.canvas.getContext('2d');
    let myChart = new Chart(this.ctx, {
      type: 'bar',
      data: {
          labels: ["January", "February", "March", "April", "May", "Jun", "July", "August", "September", "October", "November", "December"],
          datasets: [{
              label: '# of Reservations',
              data: [5,2,3, 4, 5, 7, 1, 6, 7, 4, 6, 9],
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
  }

}

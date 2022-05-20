import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../service/user.service';
import { DomSanitizer, SafeResourceUrl, } from '@angular/platform-browser';

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

  constructor(private userService: UserService, public sanitizer: DomSanitizer) {  this.responsiveOptions = [{
    breakpoint: '1024px',
    numVisible: 1,
    numScroll: 3
}];}

  ngOnInit() {
    
    this.src = "https://maps.google.com/maps?q=" + this.lat + "," + this.lng +"&t=&z=13&ie=UTF8&iwloc=&output=embed"
    this.src = this.sanitizer.bypassSecurityTrustResourceUrl(this.src);

  }

}

import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../service/user.service';

@Component({
  selector: 'app-cottage-owner-homepage',
  templateUrl: './cottage-owner-homepage.component.html',
  styleUrls: ['./cottage-owner-homepage.component.css']
})
export class CottageOwnerHomepageComponent implements OnInit {
  images; 
  responsiveOptions;

  constructor(private userService: UserService) {  this.responsiveOptions = [{
    breakpoint: '1024px',
    numVisible: 1,
    numScroll: 3
}];}

  ngOnInit() {
    this.images = [
      {random: 'Random', picture: 'https://picsum.photos/id/944/900/500'},
      {random: 'Samoa', picture: 'https://picsum.photos/id/1011/900/500'},
      {random: 'Tonga', picture: 'https://picsum.photos/id/984/900/500'},
      {random: 'Cook Island', picture: 'https://picsum.photos/id/944/900/500'},
      {random: 'Niue', picture: 'https://picsum.photos/id/1011/900/500'},
      {random: 'American Samoa', picture: 'https://picsum.photos/id/984/900/500'}
  ];
  }

}

import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../service/user.service';

@Component({
  selector: 'app-cottage-owner-homepage',
  templateUrl: './cottage-owner-homepage.component.html',
  styleUrls: ['./cottage-owner-homepage.component.css']
})
export class CottageOwnerHomepageComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit() {

  }

}

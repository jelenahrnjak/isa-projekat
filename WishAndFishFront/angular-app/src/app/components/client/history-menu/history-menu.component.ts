import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../service/auth.service';
import {UserService} from '../../../service/user.service';

@Component({
  selector: 'app-history-menu',
  templateUrl: './history-menu.component.html',
  styleUrls: ['./history-menu.component.css']
})
export class HistoryMenuComponent implements OnInit {

  constructor(private authService: AuthService,
    private userService: UserService) { }

  ngOnInit() {
  }

}

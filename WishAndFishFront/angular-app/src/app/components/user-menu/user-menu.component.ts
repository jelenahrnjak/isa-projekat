import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../service/auth.service';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.css']
})
export class UserMenuComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private userService: UserService) { }

  ngOnInit() { 
  }
 
  logout() {
    this.authService.logout();
  }

  isClient(){
    if(localStorage.getItem('role') === 'CLIENT' && localStorage.getItem('user') !== null){
      return true;
    }else{
      return false;
  }}


}

import { AuthService } from './../../service/auth.service';
import { Component, OnInit } from '@angular/core';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor( private userService: UserService, private authService: AuthService) { }

  ngOnInit() {
  }

  hasSignedIn() {
    if(localStorage.getItem('user') === null){
      return false;
    }else{
      return true;
    }
  }
 
  
  isAdmin() {
    let role = localStorage.getItem("role");
    if (role == "ROLE_ADMIN" && this.hasSignedIn()){
      return true;
    }
    return false;
  }

}

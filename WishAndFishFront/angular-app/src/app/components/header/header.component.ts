import { AuthService } from './../../service/auth.service';
import { Component, OnInit } from '@angular/core';
import {UserService} from '../../service/user.service';
import {Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor( private userService: UserService, private authService: AuthService, private router: Router,) { }

  ngOnInit() {
 
  }

  hasSignedIn() {
    if(localStorage.getItem('user') === null){
      return false;
    }else{
      return true;
    }
  }
 
  clientOrUnregistered(){
    if(localStorage.getItem('role') === 'ROLE_CLIENT' || !this.hasSignedIn()){
      return true;
    }else{
      return false;
    }
  }
    
  isClient() {
    let role = localStorage.getItem("role");
    if (role == "ROLE_CLIENT" && this.hasSignedIn()){
      return true;
    }
    return false;
  }

  isAdmin() {
    let role = localStorage.getItem("role");
    if (role == "ROLE_ADMIN" && this.hasSignedIn()){
      return true;
    }
    return false;
  }

  isCottageOwner() {
    let role = localStorage.getItem("role");
    if (role == "ROLE_COTTAGE_OWNER" && this.hasSignedIn()){
      return true;
    }
    return false;
  }

  isBoatOwner() {
    let role = localStorage.getItem("role");
    if (role == "ROLE_BOAT_OWNER" && this.hasSignedIn()){
      return true;
    }
    return false;
  }

  home() {
    let role = localStorage.getItem("role");
    switch (role) { 
      case "ROLE_COTTAGE_OWNER":
        this.router.navigate(['/cottage-profile']);
		    break 
      case "ROLE_CLIENT":
        this.router.navigate(['/client']);
        break 
      case "ROLE_BOAT_OWNER":
        this.router.navigate(['/my-boats']);
        break;
      default:
        this.router.navigate(['/']);
    }
  }

  isSelectedCottage(){
    if(window.location.pathname.split('/')[1] == "cottage-details"){
      return true;
    }else{
      return false;
    }
  }

}

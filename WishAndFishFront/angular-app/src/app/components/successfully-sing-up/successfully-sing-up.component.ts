import { AuthService } from './../../service/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-successfully-sing-up',
  templateUrl: './successfully-sing-up.component.html',
  styleUrls: ['./successfully-sing-up.component.css']
})
export class SuccessfullySingUpComponent implements OnInit {

  constructor(private authService: AuthService) { }
  token: any

  ngOnInit() {

    var url = window.location.href;
    this.token = url.split("/")[4]
    console.log(this.token)
    this.authService.verifyAccount(this.token).subscribe(
      (token: any) => {
       //  this.expired = false
     })
  }

}

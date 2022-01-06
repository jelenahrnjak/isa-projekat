import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/service';
import { OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup,FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../service';
import { Subject } from 'rxjs/Subject';
import { takeUntil } from 'rxjs/operators';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-accept-registration',
  templateUrl: './accept-registration.component.html',
  styleUrls: ['./accept-registration.component.css']
})
export class AcceptRegistrationComponent implements OnInit {
  
  users : any[];
  name : any;
  notification: DisplayMessage; 
  returnUrl: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {this.loadUnenabledUsers(); }

  ngOnInit() {
    this.route.params
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
    }); 
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.loadUnenabledUsers();
  }

  loadUnenabledUsers(){
    this.userService.getUnenabledUsers().subscribe( data => this.users = data);
  }

  acceptUser(id){
    this.userService.acceptUser(id).subscribe();
    this.loadUnenabledUsers();
  }

}

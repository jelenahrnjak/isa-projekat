import { Component, Injectable, Input, OnInit, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/service';
import { OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup,FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../service';
import { Subject } from 'rxjs/Subject';
import { takeUntil } from 'rxjs/operators';
import {MatDialog} from '@angular/material/dialog';
import { EventEmitter } from 'protractor';


interface DisplayMessage {
  msgType: string;
  msgBody: string;
}
@Component({
  selector: 'app-accept-registration',
  templateUrl: './accept-registration.component.html',
  styleUrls: ['./accept-registration.component.css']
})
@Injectable({
  providedIn: 'root'  // <- ADD THIS
})
export class AcceptRegistrationComponent implements OnInit {
  
  users : any[];
  name : any;
  notification: DisplayMessage; 
  returnUrl: string;
  openMessageBox: false;
  id : any;
  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    public dialog: MatDialog
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
    this.userService.getUnenabledUsers().subscribe(data => this.users = data);
  }

  acceptUser(id){
    this.userService.acceptUser(id).subscribe();
    this.loadUnenabledUsers();
  }

  declineUser(reasonForDeclining : any, id : any){
    console.log(id);
    var DeclinedUser = {
      "userEmail" : localStorage.getItem('id'),
      "message" : reasonForDeclining
    }
    this.userService.declineUser(DeclinedUser).subscribe();
    this.loadUnenabledUsers();
  }

  openDialog(id) {
    console.log(id);
    const dialogRef = this.dialog.open(DialogContentExampleDialog);
    localStorage.setItem('id', id);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

}

@Component({
  selector: 'dialog-content',
  templateUrl: 'dialog-content.component.html'
})
export class DialogContentExampleDialog{
  message : "";
  @Input('id') id : any;
  constructor(
    private accept : AcceptRegistrationComponent){}
  
  declineUser(){
    console.log(this.accept.id);
    this.accept.declineUser(this.message, this.id);
  }
}
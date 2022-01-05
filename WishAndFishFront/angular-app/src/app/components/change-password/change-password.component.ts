import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService, UserService } from '../../service';
import { Subject } from 'rxjs/Subject';
import { takeUntil } from 'rxjs/operators';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  title = 'Change password';
  user = {
    "email": localStorage.getItem('user'),
    "password" : "",
    "passwordRepeated"  : ""
  };

  notification: DisplayMessage; 
  returnUrl: string;
  submitted=false
  private ngUnsubscribe: Subject<void> = new Subject<void>();

  form = new FormGroup({  
    email: new FormControl(''),
    password: new FormControl('', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])), 
    passwordRepeated: new FormControl('', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])),  
  })

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.route.params
    .pipe(takeUntil(this.ngUnsubscribe))
    .subscribe((params: DisplayMessage) => {
      this.notification = params;
  }); 
  this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  this.user.email = localStorage.getItem("user")
  }

  onSubmit() {
    
    this.notification = undefined;
    this.submitted = true;
    this.user.password = this.form.get('password').value
    this.user.passwordRepeated = this.form.get('passwordRepeated').value
    this.userService.changePassword(this.user)
    .subscribe(data => { 
      this.router.navigate([this.returnUrl]);
    },
      error => {
        this.submitted = false; 
        this.notification = {msgType: 'error', msgBody: 'Passwords are not equal.'};
      });
   }

}

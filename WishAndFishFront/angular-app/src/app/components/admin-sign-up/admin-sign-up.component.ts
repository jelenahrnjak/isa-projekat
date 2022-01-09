import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService, UserService } from '../../service';
import { Subject } from 'rxjs/Subject';
import { takeUntil } from 'rxjs/operators';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-admin-sign-up',
  templateUrl: './admin-sign-up.component.html',
  styleUrls: ['./admin-sign-up.component.css']
})
export class AdminSignUpComponent implements OnInit {
  title = 'Sign up';
  form: FormGroup;
  formAdress : FormGroup;

  /**
   * Boolean used in telling the UI
   * that the form has been submitted
   * and is awaiting a response
   */
  submitted = false;
  showAddress = false;
  showReasonForRegistry = false;

  /**
   * Notification message from received
   * form request or router
   */
  notification: DisplayMessage;

  returnUrl: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {

  }

  ngOnInit() {
    this.route.params
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
      });
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.form = this.formBuilder.group({ 
      name: [''],
      surname: [''],
      email: [''],
      phoneNumber: [''],
      street : [''],
      streetNumber : [''],
      postalCode : [''],
      longitude : [''],
      latitude : [''],
      cityName : [''],
      countryName : [''],
    })
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  onSubmit() {
    /**
     * Innocent until proven guilty
     */
    this.notification = undefined;
    this.submitted = true;
    console.log(this.form.value[0]);
    var User = { 
      "id" : 232,
      "password" : "admin",
      "email" : this.form.get('email').value,
      "name" : this.form.get('name').value,
      "surname" : this.form.get('surname').value,
      "phoneNumber" : this.form.get('phoneNumber').value,
      "address" : {
        "street" : this.form.get('street').value,
        "streetNumber" : this.form.get('streetNumber').value,
        "postalCode" : this.form.get('postalCode').value,
        "longitude" : this.form.get('longitude').value,
        "latitude" : this.form.get('latitude').value, 
        "cityName" : this.form.get('cityName').value,
        "countryName" : this.form.get('countryName').value 
      },
      "roleName" : "ADMIN",
      "reasonForRegistration" : ""
  }

    this.authService.signup(User)
      .subscribe(data => {
        this.router.navigate([this.returnUrl]);
      },
        error => {
          this.submitted = false;
          console.log('Sign up error');
          this.notification = { msgType: 'error', msgBody: error['error'].message };
        });

  }

  showAddressForm() {
    this.showAddress = true;
  }

}

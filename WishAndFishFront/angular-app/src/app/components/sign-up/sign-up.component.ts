import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService, UserService } from '../../service';
import { Subject } from 'rxjs/Subject';
import { takeUntil } from 'rxjs/operators';
import Swal from 'sweetalert2';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

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
  message = ""

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
      password: ['', Validators.compose([Validators.required, Validators.minLength(8), Validators.maxLength(32), Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[-+_!@#$%^&*.,?:;<>=`~\\]\x22\x27\(\)\{\}\|\/\[\\\\?]).{8,}$')])],
      passwordRepeated: ['', Validators.compose([Validators.required, Validators.minLength(8), Validators.maxLength(32), Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[-+_!@#$%^&*.,?:;<>=`~\\]\x22\x27\(\)\{\}\|\/\[\\\\?]).{8,}$')])],
      name: ['', Validators.compose([Validators.required])],
      surname: ['', Validators.compose([Validators.required])],
      email: ['', Validators.compose([Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')])],
      phoneNumber: ['', Validators.compose([Validators.required])],
      role : [''],
      street : [''],
      streetNumber : [''],
      postalCode : [''], 
      cityName : [''],
      countryName : [''],
      reasonForRegistration : [''],
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

    if(this.form.get('password').value != this.form.get('passwordRepeated').value){
      this.message = "Passwords do not match!"
      return
    }

    this.notification = undefined;
    this.submitted = true;
    console.log(this.form.value[0]);
    var User = { 
      "id" : "",
      "password" : this.form.get('password').value,
      "email" : this.form.get('email').value,
      "name" : this.form.get('name').value,
      "surname" : this.form.get('surname').value,
      "phoneNumber" : this.form.get('phoneNumber').value,
      "address" : {
          "street" : this.form.get('street').value,
          "streetNumber" : this.form.get('streetNumber').value,
          "postalCode" : this.form.get('postalCode').value,
          "longitude" : 0.0,
          "latitude" : 0.0, 
          "cityName" : this.form.get('cityName').value,
          "countryName" : this.form.get('countryName').value    
      },
      "roleName" : this.form.get('role').value,
      "reasonForRegistration" : this.form.get('reasonForRegistration').value
  }

    if(this.form.valid){
      this.authService.signup(User)
      .subscribe(data => {
        Swal.fire({
          icon: 'success',
          title: 'Success!',
          text: 'Verification email sent. Please check your email.',
        })   
        this.router.navigate([this.returnUrl]);
      },
        error => {
          this.submitted = false;
          console.log('Sign up error');
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Email already exists.',
          })   
          this.notification = { msgType: 'error', msgBody: error['error'].message };
        });

    }
    else{
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Please fill all fields.',
      })   
    }

  }
 
  changeRole(value){
    if(value != "ROLE_CLIENT"){
      this.showReasonForRegistry = true;
    }
    else{
      this.showReasonForRegistry = false;
    }
  }

}

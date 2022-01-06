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
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  title = 'Profile informations'; 
  points = 0
  pointsNeeded = 0
  discount = 0
  program = 'REGULAR'
  user: any
  address : any
  userInfo: any
  editing = false;  

  notification: DisplayMessage; 
  returnUrl: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder) { }

    form = new FormGroup({ 
      name: new FormControl(''),
      surname: new FormControl(''),  
      email: new FormControl(''),
      phoneNumber: new FormControl(''), 
      street: new FormControl(''), 
      streetNumber: new FormControl(''), 
      postalCode: new FormControl(''), 
      cityName: new FormControl(''), 
      countryName: new FormControl('')
    })
 
    formBefore = new FormGroup({
      nameSurname: new FormControl(''),  
      email: new FormControl(''),
      phoneNumber: new FormControl(''), 
      address: new FormControl(''),   
      city: new FormControl(''), 
      country: new FormControl('')
    })

  ngOnInit() {
    this.route.params
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
    }); 
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.refreshFormBefore();
    console.log("Ovaj korisnik je ulogovan: " + this.userService.currentUser.email)
  }

  refreshForm(){
    this.userService.getUser().subscribe((data : any) => {
      this.user = data
      this.address = data.address
      this.userInfo = JSON.parse(JSON.stringify(data));  
      this.form.controls['name'].setValue(this.user.name)
      this.form.controls['surname'].setValue(this.user.surname)  
      this.form.controls['email'].setValue(this.user.email)
      this.form.controls['phoneNumber'].setValue(this.user.phoneNumber) 
      this.form.controls['street'].setValue(this.address.street)
      this.form.controls['streetNumber'].setValue(this.address.streetNumber)
      this.form.controls['postalCode'].setValue(this.address.postalCode)
      this.form.controls['cityName'].setValue(this.address.cityName)
      this.form.controls['countryName'].setValue(this.address.countryName)
    });   
  }

  refreshFormBefore(){
    this.userService.getUser().subscribe((data : any) => {
      this.user = data
      console.log(data)
      this.points = data.points
      this.discount = data.discount
      this.program = data.loyalityProgram
      this.address = data.address
      this.pointsNeeded = data.neededPoints
      this.userInfo = JSON.parse(JSON.stringify(data));  
      this.formBefore.controls['nameSurname'].setValue(this.user.name + " " + this.user.surname)  
      this.formBefore.controls['email'].setValue(this.user.email)
      this.formBefore.controls['phoneNumber'].setValue(this.user.phoneNumber) 
      this.formBefore.controls['address'].setValue(this.address.street + " " + this.address.streetNumber) 
      this.formBefore.controls['city'].setValue(this.address.cityName + " " + this.address.postalCode)
      this.formBefore.controls['country'].setValue(this.address.countryName)
    });   
  }

  changeForm() { 
    this.notification = undefined; 
    this.refreshForm();
    this.refreshFormBefore();
    this.editing = !this.editing;

  }

  onSubmit() {   
    this.user.name = this.form.get('name').value
    this.user.surname = this.form.get('surname').value
    this.user.phoneNumber = this.form.get('phoneNumber').value
    this.address.street = this.form.get('street').value
    this.address.streetNumber = this.form.get('streetNumber').value
    this.address.postalCode = this.form.get('postalCode').value
    this.address.cityName = this.form.get('cityName').value
    this.address.countryName = this.form.get('countryName').value
    this.user.address= this.address 

    this.userService.update(this.user).subscribe((data) => {
      this.user = data ;
      this.userInfo = JSON.parse(JSON.stringify(data));
      this.editing = false;
      this.refreshFormBefore()
      
    })
  }


}

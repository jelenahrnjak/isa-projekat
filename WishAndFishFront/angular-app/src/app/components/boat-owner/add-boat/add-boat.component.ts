import { BoatService } from 'src/app/service/boat.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService, UserService } from '../../../service';
import { Subject } from 'rxjs/Subject';
import { takeUntil } from 'rxjs/operators';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-add-boat',
  templateUrl: './add-boat.component.html',
  styleUrls: ['./add-boat.component.css']
})
export class AddBoatComponent implements OnInit {
  form: FormGroup;
  formAdress : FormGroup;
  notification: DisplayMessage;
  returnUrl: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  selectedFile = null;

  constructor(private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private boatService: BoatService) { }

  ngOnInit() {
    this.route.params
    .pipe(takeUntil(this.ngUnsubscribe))
    .subscribe((params: DisplayMessage) => {
      this.notification = params;
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/my-boats';

    
    this.form = this.formBuilder.group({ 
      name: ['', Validators.compose([Validators.required, Validators.minLength(0)])],
      type: ['', Validators.compose([Validators.required, Validators.minLength(0)])],
      price: ['' ,Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+)$')])],
      length: ['' ,Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+)$')])],
      engineNumber : ['' ,Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+)$')])],
      enginePower : ['',Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+)$')])],
      maxSpeed : ['',Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+)$')])],
      description : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
      capacity : ['',Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+)$')])],
      // pricePerHour : ['',Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])],
      street: ['', Validators.compose([Validators.required, Validators.minLength(0)])],
      streetNumber : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
      postalCode : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
      longitude : ['',Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])],
      latitude : ['',Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])],
      cityName : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
      countryName : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
      cancellationConditions: ['', Validators.compose([Validators.required, Validators.minLength(0)])],
    })
  }

  onSubmit() {
    this.notification = undefined;
    var boat = { 
      "name" : this.form.get('name').value,
      "type" : this.form.get('type').value,
      "length" : this.form.get('length').value,
      "engineNumber" : this.form.get('engineNumber').value,
      "enginePower" : this.form.get('enginePower').value,
      "maxSpeed" : this.form.get('maxSpeed').value,
      "description" : this.form.get('description').value,
      "capacity" : this.form.get('capacity').value,
      "pricePerHour" : this.form.get('price').value,

      "address" : {
          "street" : this.form.get('street').value,
          "streetNumber" : this.form.get('streetNumber').value,
          "postalCode" : this.form.get('postalCode').value,
          "longitude" : this.form.get('longitude').value,
          "latitude" : this.form.get('latitude').value, 
          "cityName" : this.form.get('cityName').value,
          "countryName" : this.form.get('countryName').value    
      },
      "ownerEmail" : localStorage.getItem("user"),
      "cancellationConditions" : this.form.get('cancellationConditions').value,
      "coverImage": this.selectedFile
  }

  if(this.form.valid){
      this.boatService.addBoat(boat)
    .subscribe(data => {
      this.router.navigate([this.returnUrl]);
    },
      error => {
        console.log('Add boat error');
      
      });
      console.log(boat);
  }
  
  }

  selectImage(event){
    this.selectedFile = event.target.files[0].name;
  }

}

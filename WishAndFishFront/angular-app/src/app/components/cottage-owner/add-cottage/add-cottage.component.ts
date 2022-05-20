import { CottageService } from 'src/app/service/cottage.service';
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
  selector: 'app-add-cottage',
  templateUrl: './add-cottage.component.html',
  styleUrls: ['./add-cottage.component.css']
})
export class AddCottageComponent implements OnInit {
  showAddress = false;
  showFirstForm = true;
  showRules = false;
  form: FormGroup;
  formAdress : FormGroup;
  notification: DisplayMessage;
  selectedFile = null;

  returnUrl: string;
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  constructor( private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private cottageService: CottageService) { }

  ngOnInit() {
    this.route.params
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
      });

      this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/cottage-profile';


      this.form = this.formBuilder.group({ 
        name: ['', Validators.compose([Validators.required, Validators.minLength(0)])],
        description : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
        rooms: ['' ,Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+)$')])],
        beds: ['' ,Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+)$')])],
        price: ['' ,Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+)$')])],
        street: ['', Validators.compose([Validators.required, Validators.minLength(0)])],
        streetNumber : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
        postalCode : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
        longitude : ['',Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])],
        latitude : ['',Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])],
        cityName : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
        countryName : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
       
      })
  }

  
  showAddressForm() {
    this.showAddress = true;
  }

  showMainForm() {
    this.showAddress = false;
  }

  
  showRulesForm() {
    this.showRules = true;
  }


  onSubmit() {
    this.notification = undefined;
    var Cottage = { 
      "name" : this.form.get('name').value,
      "description" : this.form.get('description').value,
      "numberOfRooms" :  this.form.get('rooms').value,
      "bedsPerRoom":  this.form.get('beds').value,
      "price" : this.form.get('price').value,
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
      "coverImage": this.selectedFile

  }

  if(this.form.valid){

    this.cottageService.addCottage(Cottage)
    .subscribe(data => {
      this.router.navigate([this.returnUrl]);
    },
      error => {
        console.log('Add cottage error');
      
      });
      console.log(Cottage);
    }
  }


  selectImage(event){
    this.selectedFile = event.target.files[0].name;
  }

}

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

      this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';


      this.form = this.formBuilder.group({ 
        name: [''],
        description: [''],
        price: [''],
        street: [''],
        streetNumber : [''],
        postalCode : [''],
        longitude : [''],
        latitude : [''],
        cityName : [''],
        countryName : ['']
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
    /**
     * Innocent until proven guilty
     */
    this.notification = undefined;
    var Cottage = { 
      "name" : this.form.get('name').value,
      "description" : this.form.get('description').value,
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

  }

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

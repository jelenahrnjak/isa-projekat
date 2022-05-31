import { Boat } from 'src/app/model/boat.model';
import { BoatService } from 'src/app/service/boat.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService, UserService } from '../../../service';
import { Subject } from 'rxjs/Subject';
import { takeUntil } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { MapsAPILoader, MouseEvent } from '@agm/core';

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
  lat = 45.23731467405363;
  lng = 19.842083286315937;

  private geoCoder;
  address: string;
  zoom: number;


  longitude: number = 0;
  latitude: number = 0;


  boat = new Boat()
  
  constructor(private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private boatService: BoatService) { }

  ngOnInit() {
    this.geoCoder = new google.maps.Geocoder();


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
      // street: ['', Validators.compose([Validators.required, Validators.minLength(0)])],
      // streetNumber : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
      // postalCode : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
      // longitude : ['',Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])],
      // latitude : ['',Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])],
      // cityName : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
      // countryName : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
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
      "pricePerDay" : this.form.get('price').value,

      "address" : {
        "street" : this.boat.address.street,
        "streetNumber" : this.boat.address.streetNumber,
        "postalCode" : this.boat.address.postalCode,
        "longitude" : this.longitude,
        "latitude" : this.latitude,
        "cityName" : this.boat.address.cityName,
        "countryName" : this.boat.address.countryName   
   },
      "ownerEmail" : localStorage.getItem("user"),
      "cancellationConditions" : this.form.get('cancellationConditions').value,
      "coverImage": this.selectedFile
  }

  if(this.form.valid  && this.longitude !== 0 && this.latitude !== 0){
      this.boatService.addBoat(boat)
    .subscribe(data => {
      this.router.navigate([this.returnUrl]);
    },
      error => {
        console.log('Add boat error');
      
      });
      console.log(boat);
  } else{
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Select addrress!',
    })
  }
  
  }

  selectImage(event){
    this.selectedFile = event.target.files[0].name;
  }



  
  markerDragEnd($event: MouseEvent) {
    console.log($event.coords.lat)
    console.log($event.coords.lng)

    this.latitude= $event.coords.lat
    this.longitude = $event.coords.lng

    this.getAddress($event.coords.lat, $event.coords.lng);
    console.log(this.boat)
  }


  getAddress(latitude, longitude) {
    this.geoCoder.geocode({ 'location': { lat: latitude, lng: longitude } }, (results, status) => {
      //console.log(results);
      //console.log(status);
      if (status === 'OK') {
        if (results[0]) {
          this.zoom = 12;
          this.address = results[0].formatted_address;
          console.log(results[0])

          if(results[0].address_components.filter(ac=>~ac.types.indexOf('locality')) != undefined){
            this.boat.address.cityName = results[0].address_components.filter(ac=>~ac.types.indexOf('locality'))[0].long_name
          }

          if(results[0].address_components.filter(ac=>~ac.types.indexOf('country')) != undefined){
            this.boat.address.countryName = results[0].address_components.filter(ac=>~ac.types.indexOf('country'))[0].long_name
          }

          if( results[0].address_components.filter(ac=>~ac.types.indexOf('street_number')) != undefined){
            this.boat.address.streetNumber = results[0].address_components.filter(ac=>~ac.types.indexOf('street_number'))[0].long_name
          }

          if(results[0].address_components.filter(ac=>~ac.types.indexOf('route')) != undefined){
            this.boat.address.street = results[0].address_components.filter(ac=>~ac.types.indexOf('route'))[0].long_name
          }

          if(results[0].address_components.filter(ac=>~ac.types.indexOf('postal_code')) != undefined){
            this.boat.address.postalCode = results[0].address_components.filter(ac=>~ac.types.indexOf('postal_code'))[0].long_name
          }

        } else {
          window.alert('No results found');
        }
      } else {
        window.alert('Geocoder failed due to: ' + status);
      }

    });
  }


}

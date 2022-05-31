import { Cottage } from './../../../model/cottage';
import { CommentService } from './../../../service/comment.service';
import { CottageService } from 'src/app/service/cottage.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService, UserService } from '../../../service';
import { Subject } from 'rxjs/Subject';
import { takeUntil } from 'rxjs/operators';
import { MapsAPILoader, MouseEvent } from '@agm/core';
import Swal from 'sweetalert2';

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
  lat = 45.23731467405363;
  lng = 19.842083286315937;
  returnUrl: string;
  private geoCoder;
  address: string;
  zoom: number;


  longitude: number = 0;
  latitude: number = 0;


  cottage = new Cottage()
  

  private ngUnsubscribe: Subject<void> = new Subject<void>();
  constructor( private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private cottageService: CottageService) { }

  ngOnInit() {
    this.geoCoder = new google.maps.Geocoder();



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
        // street: ['', Validators.compose([Validators.required, Validators.minLength(0)])],
        // streetNumber : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
        // postalCode : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
        // longitude : ['',Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])],
        // latitude : ['',Validators.compose([Validators.required, Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])],
        // cityName : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
        // countryName : ['', Validators.compose([Validators.required, Validators.minLength(0)])],
       
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
    // this.cottage.name = this.form.get('name').value
    // this.cottage.description = this.form.get('description').value
    // this.cottage.numberOfRooms = this.form.get('numberOfRooms').value
    // this.cottage.bedsPerRoom = this.form.get('bedsPerRoom').value
    // this.cottage.price = this.form.get('price').value
    // this.cottage.ownerEmail = this.form.get('price').value
    // this.cottage.price = this.form.get('price').value

    var Cottage = { 
      "name" : this.form.get('name').value,
      "description" : this.form.get('description').value,
      "numberOfRooms" :  this.form.get('rooms').value,
      "bedsPerRoom":  this.form.get('beds').value,
      "price" : this.form.get('price').value,
      "address" : {
           "street" : this.cottage.address.street,
           "streetNumber" : this.cottage.address.streetNumber,
           "postalCode" : this.cottage.address.postalCode,
           "longitude" : this.longitude,
           "latitude" : this.latitude,
           "cityName" : this.cottage.address.cityName,
           "countryName" : this.cottage.address.countryName   
      },
      "ownerEmail" : localStorage.getItem("user"),
      "coverImage": this.selectedFile

  }

  if(this.form.valid && this.longitude !== 0 && this.latitude !== 0){

    this.cottageService.addCottage(Cottage)
    .subscribe(data => {
      this.router.navigate([this.returnUrl]);
    },
      error => {
        console.log('Add cottage error');
      
      });
      console.log(Cottage);
    }
    else{
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
    console.log(this.cottage)
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
            this.cottage.address.cityName = results[0].address_components.filter(ac=>~ac.types.indexOf('locality'))[0].long_name
          }

          if(results[0].address_components.filter(ac=>~ac.types.indexOf('country')) != undefined){
            this.cottage.address.countryName = results[0].address_components.filter(ac=>~ac.types.indexOf('country'))[0].long_name
          }

          if( results[0].address_components.filter(ac=>~ac.types.indexOf('street_number')) != undefined){
            this.cottage.address.streetNumber = results[0].address_components.filter(ac=>~ac.types.indexOf('street_number'))[0].long_name
          }

          if(results[0].address_components.filter(ac=>~ac.types.indexOf('route')) != undefined){
            this.cottage.address.street = results[0].address_components.filter(ac=>~ac.types.indexOf('route'))[0].long_name
          }

          if(results[0].address_components.filter(ac=>~ac.types.indexOf('postal_code')) != undefined){
            this.cottage.address.postalCode = results[0].address_components.filter(ac=>~ac.types.indexOf('postal_code'))[0].long_name
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

import { Boat } from 'src/app/model/boat.model';
import { BoatService } from 'src/app/service/boat.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-boat-basic-info',
  templateUrl: './edit-boat-basic-info.component.html',
  styleUrls: ['./edit-boat-basic-info.component.css']
})
export class EditBoatBasicInfoComponent implements OnInit {
  id: any = -1;
  boat : Boat = new Boat();

  constructor(private formBuilder: FormBuilder,
    private boatService: BoatService,
    private sanitizer : DomSanitizer,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;
    this.boatService.findBoat(this.id).subscribe((data) => {
      this.boat = data;
      console.log(this.boat)
    });
  }

  cancel(){
    this.router.navigate(['/boat-details/' + this.id]);
  }

  submit(){

    var letters = /[a-zA-Z]/;

    if(this.boat.name == "" || this.boat.type == "" || this.boat.pricePerHour == "" || this.boat.description == "" || this.boat.cancellationConditions == "" || this.boat.maximumPeople == "" || this.boat.length == "" || this.boat.engineNumber == "" || this.boat.enginePower == "" || this.boat.maxSpeed == "" || this.boat.capacity == ""
    || this.boat.address.cityName == "" || this.boat.address.cityName == "" || this.boat.address.latitude == "" || this.boat.address.longitude == "" || this.boat.address.postalCode == "" || this.boat.address.street == "" || this.boat.address.streetNumber == ""){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Fill all fields!',
      })    
      console.log(this.boat)
    }
    else if(letters.test(this.boat.pricePerHour) || letters.test(this.boat.length) || letters.test(this.boat.engineNumber) || letters.test(this.boat.enginePower) || letters.test(this.boat.maxSpeed) || letters.test(this.boat.capacity) || letters.test(this.boat.address.latitude) || letters.test(this.boat.address.longitude)){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Fill in the fields correctly, some of them should not contain letters!',
      })  
    }
    else{
      this.boatService.editBasicInfo(this.boat).subscribe((data) =>{
        this.router.navigate(['/boat-details/' + this.id]);
      });
    }

  }

}

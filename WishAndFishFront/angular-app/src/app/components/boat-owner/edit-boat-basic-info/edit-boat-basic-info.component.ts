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

    if(this.boat.name == "" || this.boat.type == "" || this.boat.pricePerHour == undefined || this.boat.description == "" || this.boat.cancellationConditions == "" || this.boat.maximumPeople == undefined || this.boat.length == undefined || this.boat.engineNumber == undefined || this.boat.enginePower == undefined || this.boat.maxSpeed == undefined || this.boat.maxSpeed == undefined || this.boat.capacity == undefined
    || this.boat.address.cityName == "" || this.boat.address.cityName == "" || this.boat.address.latitude == undefined || this.boat.address.longitude == undefined || this.boat.address.postalCode == undefined || this.boat.address.street == undefined || this.boat.address.streetNumber == undefined){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Fill all fields!',
      })    }
    else{
      this.boatService.editBasicInfo(this.boat).subscribe((data) =>{
        this.router.navigate(['/boat-details/' + this.id]);
      });
    }

  }

}

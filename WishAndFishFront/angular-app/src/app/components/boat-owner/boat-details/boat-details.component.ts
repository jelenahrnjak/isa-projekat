import { Boat } from './../../../model/boat.model';
import { ClientService } from './../../../service/client.service';
import { AppointmentService } from './../../../service/appointment.service';
import { RuleService } from './../../../service/rule.service';
import { AdditionalServicesService } from './../../../service/additional-services.service';
import { ImageService } from './../../../service/image.service';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer, SafeStyle } from '@angular/platform-browser';
import { BoatService } from './../../../service/boat.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-boat-details',
  templateUrl: './boat-details.component.html',
  styleUrls: ['./boat-details.component.css']
})
export class BoatDetailsComponent implements OnInit {

  id: any
  boat: Boat = new Boat()
  userImage: SafeStyle;
  images: any;
  userRole = localStorage.getItem('role');

  constructor(private route: ActivatedRoute,
    private boatService: BoatService,
    private sanitizer : DomSanitizer,
    private router: Router,
    private http : HttpClient,
    private imageService: ImageService,
    private additionalService: AdditionalServicesService,
    private ruleService : RuleService,
    private appointmentService: AppointmentService,
    private clientService : ClientService,) { }

  ngOnInit() {

    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.boatService.findBoat(this.id).subscribe((data) => {
      this.boat = data;
      this.userImage = this.sanitizer.bypassSecurityTrustStyle('url(assets/Images/' + data.coverImage +')');
      console.log(this.boat)
    });


    this.imageService.findImagesBoat(this.id).subscribe((data : any) => {
      this.images = data;
      });
  }


  
  ifOwner(){
    if(this.userRole === 'BOAT_OWNER'){
      return true;
    }
    return false;
   }

}

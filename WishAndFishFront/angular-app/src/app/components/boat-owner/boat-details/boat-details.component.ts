import { FishingEquipmentService } from './../../../service/fishing-equipment.service';
import { NavigationEquipmentService } from './../../../service/navigation-equipment.service';
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
import Swal from 'sweetalert2';

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
  additionalServices : any;

  newAdditionalService = {
    "id": "",
    "name" : "",
    "price" : ""
  }

  rules: any;
  newRule={
    "id": "",
    "content": ""
  }

  navigationEquipments: any
  fishingEquipments: any

  constructor(private route: ActivatedRoute,
    private boatService: BoatService,
    private sanitizer : DomSanitizer,
    private router: Router,
    private http : HttpClient,
    private imageService: ImageService,
    private additionalService: AdditionalServicesService,
    private ruleService : RuleService,
    private appointmentService: AppointmentService,
    private clientService : ClientService,
    private navigationEquipmentService: NavigationEquipmentService,
    private fishingEquipmensService: FishingEquipmentService) { }

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

  
    this.additionalService.findAdditionalServicesBoat(this.id).subscribe((data : any) => {
      this.additionalServices = data;
    });


    this.ruleService.findRulesBoat(this.id).subscribe((data : any) => {
      this.rules = data;
      });

    this.navigationEquipmentService.findNavigation(this.id).subscribe((data : any) => {
      this.navigationEquipments = data;
      });

      this.fishingEquipmensService.findNavigation(this.id).subscribe((data : any) => {
        this.fishingEquipments = data;
        });
  }


  
  ifOwner(){
    if(this.userRole === 'BOAT_OWNER'){
      return true;
    }
    return false;
   }

   deleteImage(path){
    console.log(path)

    this.imageService.deleteImage(path)
    .subscribe(data => {
      console.log(data)
      
      window.location.reload();
    });
    }


    addService(){

      var letters = /[a-zA-Z]/;
  
      if(letters.test(this.newAdditionalService.price) || this.newAdditionalService.name.length == 0 || this.newAdditionalService.price.length == 0){
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Incorrectly filled fields!',
        })    }
      else{
        this.newAdditionalService.id = this.id;
        this.additionalService.addServiceBoat(this.newAdditionalService).subscribe(() =>{
        });      
        window.location.reload();
        this.newAdditionalService.name = ""
        this.newAdditionalService.price = ""
      }
    }

    deleteServices(id){
      console.log(id)
  
      this.additionalService.deleteAdditionalService(id)
      .subscribe(data => {
        window.location.reload();
      });
      }

    
  addRule(){
    this.newRule.id = this.id;
    this.ruleService.addRuleBoat(this.newRule).subscribe(() =>{
    });      
    window.location.reload();
    this.newRule.content = ""
  }

  
  deleteRule(id){
    console.log(id)

    this.ruleService.deleteRule(id)
    .subscribe(data => {
      window.location.reload();
    });
    }

}

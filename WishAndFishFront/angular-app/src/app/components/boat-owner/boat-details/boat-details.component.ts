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
import { formatDate } from '@angular/common';

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
  //today's date
  todayDate:Date = new Date();
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

  imageDto = {
    "path": "",
    "cottageId": ""
  };
  selectedFile = null;

  navigationEquipments: any
  fishingEquipments: any


  newEquipment={
    "id": "",
    "name": ""
  }

  startDate: String | any;
  endDate: String | any;
  startTime: any;
  endTime: any;
  src
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
    private fishingEquipmensService: FishingEquipmentService,) { }

  ngOnInit() {

    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.boatService.findBoat(this.id).subscribe((data) => {
      this.boat = data;
      // this.userImage = this.sanitizer.bypassSecurityTrustStyle('url(assets/Images/' + data.coverImage +')');
      this.userImage = 'url(assets/Images/' + data.coverImage +')';
      this.src = "https://maps.google.com/maps?q=" + this.boat.address.latitude + "," + this.boat.address.longitude +"&t=&z=13&ie=UTF8&iwloc=&output=embed"
      this.src = this.sanitizer.bypassSecurityTrustResourceUrl(this.src);
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

    if(this.userRole === 'ROLE_CLIENT'){
      this.clientService.checkSubscription(this.id, 'boat').subscribe((data:boolean) =>{
        this.boat.isSubscribed = data; 
      })
    }

  }


  
  ifOwner(){
    if(this.userRole === 'ROLE_BOAT_OWNER'){
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

    selectImage(event){
      this.selectedFile = event.target.files[0].name;
    }
  
    addImage(){
      this.imageDto.path = this.selectedFile;
      this.imageDto.cottageId = this.id;
      this.imageService.addImageBoat(this.imageDto).subscribe(() =>{
  
      });      
      window.location.reload();
  
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

      
      deleteNavigationEquipment(id){
      console.log(id)
  
      this.navigationEquipmentService.deleteNavigationEquipment(id)
      .subscribe(data => {
        window.location.reload();
      });
      }

      deleteFishingEquipment(id){
        console.log(id)
    
        this.fishingEquipmensService.deleteFishingEquipment(id)
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


    
    addNavigationEquipment(){
  
      if(this.newEquipment.name.length == 0){
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Incorrectly filled fields!',
        })    }
      else{
        this.newEquipment.id = this.id;
        this.navigationEquipmentService.addEquipment(this.newEquipment).subscribe(() =>{
        });      
        window.location.reload();
        this.newEquipment.name = ""
      }
    }


    addFishingEquipment(){
  
      if(this.newEquipment.name.length == 0){
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Incorrectly filled fields!',
        })    }
      else{
        this.newEquipment.id = this.id;
        this.fishingEquipmensService.addEquipment(this.newEquipment).subscribe(() =>{
        });      
        window.location.reload();
        this.newEquipment.name = ""
      }
    }



    editAvailability(){
  
      if(start >= end){
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Start date is greater or equal then end date!',
        }) 
      }
     else{
      console.log(this.startTime + " " + this.endTime)
  
      if(this.startDate == undefined || this.endDate == undefined || this.startTime == undefined || this.endTime == undefined || this.startTime == "" || this.endTime == ""){
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Please fill in all fields!',
        }) 
      }
      else{

        var start = formatDate(this.startDate,'dd-MM-yyyy','en_US');
        var end  = formatDate(this.endDate,'dd-MM-yyyy','en_US');
     

        var dto = {
          "id": this.id,
          "startDate": start + " " + this.startTime,
          "endDate": end  + " " + this.endTime
        }
        this.appointmentService.editAvailabilityBoat(dto).subscribe((data : any) => {
          // console.log(data)
          this.startTime = "";
          this.endTime = "";
          this.startDate = "";
          this.endDate = ""
        });
      }
    
    }
     }


     subscribe(){ 
      console.log('uradio')
 
       this.clientService.subscribeToBoat(this.id, localStorage.getItem('user')).subscribe(
         (data) => {  
           this.boat.isSubscribed = true;
           alert("Successfully subscribed") 
         },
         (err) => {  
           alert('Already subscribed!') 
         })  
    }
 
    unsubscribe(){ 
     this.clientService.unsubscribeFromBoat(this.id, localStorage.getItem('user')).subscribe(
       (data) => {  
         this.boat.isSubscribed = false;
         alert("Successfully unsubscribed") 
       },
       (err) => {  
         alert('Already unsubscribed!') 
       })  
    }

}

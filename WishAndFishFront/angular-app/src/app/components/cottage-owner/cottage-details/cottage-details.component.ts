import { AppointmentService } from './../../../service/appointment.service';
import { RuleService } from './../../../service/rule.service';
import { AdditionalServicesService } from './../../../service/additional-services.service';
import { ImageService } from 'src/app/service/image.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CottageService } from 'src/app/service/cottage.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cottage } from 'src/app/model/cottage';
import { SafeStyle,DomSanitizer } from '@angular/platform-browser';
import Swal from 'sweetalert2';
import { formatDate } from '@angular/common';
import { ClientService } from './../../../service/client.service'

@Component({
  selector: 'app-cottage-details',
  templateUrl: './cottage-details.component.html',
  styleUrls: ['./cottage-details.component.css']
})
export class CottageDetailsComponent implements OnInit {
  id: any = -1;
  cottage : Cottage = new Cottage();
  userImage: SafeStyle;
  image: SafeStyle;
  editBasicInfo: boolean = false;
  mainInfo: boolean = true;
  selectedFile = null;
  startDate: String | any;
  endDate: String | any;
  startTime: any;
  endTime: any;
  userRole = localStorage.getItem('role');


  imageDto = {
    "path": "",
    "cottageId": ""
  };

  newAdditionalService = {
    "id": "",
    "name" : "",
    "price" : ""
  }

  newRule={
    "id": "",
    "content": ""
  }

  additionalServices : any;
  rules: any;
  images: any;
  appointments: any;

  //today's date
todayDate:Date = new Date();


  constructor(private route: ActivatedRoute,
    private cottageService: CottageService,
    private sanitizer : DomSanitizer,
    private router: Router,
    private http : HttpClient,
    private imageService: ImageService,
    private additionalService: AdditionalServicesService,
    private ruleService : RuleService,
    private appointmentService: AppointmentService,
    private clientService : ClientService,
    ) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.cottageService.findCottage(this.id).subscribe((data) => {
      this.cottage = data;
      this.userImage = this.sanitizer.bypassSecurityTrustStyle('url(assets/Images/' + data.coverImage +')');
      console.log(this.cottage)
    });

    this.imageService.findImages(this.id).subscribe((data : any) => {
      this.images = data;
      });

    this.additionalService.findAdditionalServices(this.id).subscribe((data : any) => {
      this.additionalServices = data;
    });

     this.ruleService.findRules(this.id).subscribe((data : any) => {
      this.rules = data;
      });

    this.appointmentService.findAppointments(this.id).subscribe((data : any) => {
      this.appointments = data;
      });

    
    if(this.userRole === 'CLIENT'){
      this.clientService.checkSubscription(this.id, 'cottage').subscribe((data:boolean) =>{
        this.cottage.isSubscribed = data; 
      })
    }
  }

  

  // editInfo(){
  //   this.router.navigate(['/edit-cottage-basic-info/'+this.id]);
  // }

  // showAppointments(){
  //   this.router.navigate(['/show-free-appointments/'+this.id]);
  // }

  selectImage(event){
    this.selectedFile = event.target.files[0].name;
  }

  addImage(){
    this.imageDto.path = this.selectedFile;
    this.imageDto.cottageId = this.id;
    this.imageService.addImage(this.imageDto).subscribe(() =>{

    });      
    window.location.reload();

  }

  deleteServices(id){
    console.log(id)

    this.additionalService.deleteAdditionalService(id)
    .subscribe(data => {
      window.location.reload();
    });
    }
  

    deleteRule(id){
      console.log(id)
  
      this.ruleService.deleteRule(id)
      .subscribe(data => {
        window.location.reload();
      });
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
      this.additionalService.addService(this.newAdditionalService).subscribe(() =>{
      });      
      window.location.reload();
      this.newAdditionalService.name = ""
      this.newAdditionalService.price = ""
    }
  }

  addRule(){
    this.newRule.id = this.id;
    this.ruleService.addRule(this.newRule).subscribe(() =>{
    });      
    window.location.reload();
    this.newRule.content = ""
  }

  editAvailability(){
    console.log(this.startDate + " " + this.endDate)


    var start = formatDate(this.startDate,'dd-MM-yyyy','en_US');
    var end  = formatDate(this.endDate,'dd-MM-yyyy','en_US');
 

    if(start >= end){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Start date is greater or equal then end date!',
      }) 
    }
   else{
    var dto = {
      "id": this.id,
      "startDate": start + " " + this.startTime,
      "endDate": end  + " " + this.endTime
    }

    this.appointmentService.editAvailability(dto).subscribe((data : any) => {
        // console.log(data)
        this.startTime = "";
        this.endTime = "";
        this.startDate = "";
        this.endDate = ""
      });
  }
   }
  
  //  addAction(){
  //     this.router.navigate(['/add-action/'+this.id]);

  //  }
  
   ifOwner(){
    if(this.userRole === 'COTTAGE_OWNER'){
      return true;
    }
    return false;
   }
    
   subscribe(){ 
     console.log('uradio')

      this.clientService.subscribeToCottage(this.id, localStorage.getItem('user')).subscribe(
        (data) => {  
          this.cottage.isSubscribed = true;
          alert("Successfully subscribed") 
        },
        (err) => {  
          alert('Already subscribed!') 
        })  
   }

   unsubscribe(){ 
    this.clientService.unsubscribeFromCottage(this.id, localStorage.getItem('user')).subscribe(
      (data) => {  
        this.cottage.isSubscribed = false;
        alert("Successfully unsubscribed") 
      },
      (err) => {  
        alert('Already unsubscribed!') 
      })  
   }

}

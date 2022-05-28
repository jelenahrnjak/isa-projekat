import { AppointmentService } from './../../../service/appointment.service';
import { RuleService } from './../../../service/rule.service';
import { AdditionalServicesService } from './../../../service/additional-services.service';
import { ImageService } from 'src/app/service/image.service';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';
import { BoatService } from 'src/app/service/boat.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { formatDate } from '@angular/common';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-boat-sidebar',
  templateUrl: './boat-sidebar.component.html',
  styleUrls: ['./boat-sidebar.component.css']
})
export class BoatSidebarComponent implements OnInit {
  id: any;
  startDate: String | any;
  endDate: String | any;
  startTime: any;
  endTime: any;
  todayDate:Date = new Date();

  constructor(private route: ActivatedRoute,
    private boatService: BoatService,
    private sanitizer : DomSanitizer,
    private router: Router,
    private http : HttpClient,
    private imageService: ImageService,
    private additionalService: AdditionalServicesService,
    private ruleService : RuleService,
    private appointmentService: AppointmentService) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;
    this.startTime = "14:00"
    this.endTime = "12:00"
  }

  home(){
    this.router.navigate(['/boat-details/'+this.id]);

  }

  addAction(){
    this.router.navigate(['/add-boat-action/'+this.id]);

  }

  showFreeAppointments(){
    this.router.navigate(['/show-free-appointments-boat/'+this.id]);

  }

  editInfo(){
    this.router.navigate(['/edit-boat-basic-info/'+this.id]);
  }

  reservationHistory(){
    this.router.navigate(['/boat-reservation-history/'+this.id]);

  }

  report(){
    this.router.navigate(['/boat-report/'+this.id]);

  }


  editAvailability(){
    console.log(this.startDate + " " + this.endDate)
    console.log(this.startTime + " " + this.endTime)

    if(this.startDate != undefined || this.endDate != undefined){
      console.log("uslo")
      var start = formatDate(this.startDate,'dd-MM-yyyy','en_US');
      var end  = formatDate(this.endDate,'dd-MM-yyyy','en_US');
   
  
      if(this.startDate >= this.endDate){
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
  
      this.appointmentService.editAvailabilityBoat(dto).subscribe((data : any) => {
          // console.log(data)
          this.startTime = "";
          this.endTime = "";
          this.startDate = "";
          this.endDate = ""
          setTimeout(() => {window.location.reload()}, 3000); 

        });

    }
    }
    else{
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Select all dates!',
      }) 
    }

    }
   
}

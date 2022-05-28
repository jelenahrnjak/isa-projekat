import { AppointmentService } from './../../../service/appointment.service';
import { RuleService } from './../../../service/rule.service';
import { AdditionalServicesService } from './../../../service/additional-services.service';
import { HttpClient } from '@angular/common/http';
import { ImageService } from './../../../service/image.service';
import { CottageService } from './../../../service/cottage.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { formatDate } from '@angular/common';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  startDate: String | any;
  endDate: String | any;
  startTime: any;
  endTime: any;
  id: any;
  todayDate:Date = new Date();

  constructor(private route: ActivatedRoute,
    private cottageService: CottageService,
    private sanitizer : DomSanitizer,
    private router: Router,
    private http : HttpClient,
    private imageService: ImageService,
    private additionalService: AdditionalServicesService,
    private ruleService : RuleService,
    private appointmentService: AppointmentService
    ) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;
    this.startTime = "14:00"
    this.endTime = "12:00"
  }

  addAction(){
    this.router.navigate(['/add-action/'+this.id]);

  }

  editInfo(){
    this.router.navigate(['/edit-cottage-basic-info/'+this.id]);
  }

  showAppointments(){
    this.router.navigate(['/show-free-appointments/'+this.id]);
  }

  home(){
    this.router.navigate(['/cottage-details/'+this.id]);

  }

  reservationHistory(){
    this.router.navigate(['/cottage-reservation-history/'+this.id]);

  }

  report(){
    this.router.navigate(['/cottage-report/'+this.id]);

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
  
      this.appointmentService.editAvailability(dto).subscribe((data : any) => {
          // console.log(data)
          this.startTime = "";
          this.endTime = "";
          this.startDate = "";
          this.endDate = ""
        });
        setTimeout(() => {window.location.reload()}, 2000); 
        this.startTime = "";
        this.endTime = "";
        this.startDate = "";
        this.endDate = ""
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

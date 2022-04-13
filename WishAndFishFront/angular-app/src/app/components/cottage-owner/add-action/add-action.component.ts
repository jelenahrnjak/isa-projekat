import { AppointmentService } from 'src/app/service/appointment.service';
import { AdditionalServicesService } from './../../../service/additional-services.service';
import { Cottage } from './../../../model/cottage';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { CottageService } from './../../../service/cottage.service';
import { FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-add-action',
  templateUrl: './add-action.component.html',
  styleUrls: ['./add-action.component.css']
})
export class AddActionComponent implements OnInit {
  id: any = -1;
  showAddress = false;
  showFirstForm = true;
  showRules = false;
  cottage : Cottage = new Cottage();
  additionalServices : any;
  selectedServices : any[] = []
  todayDate:Date = new Date();
  startDate: any;
  endDate: any;
  expirationDate: any;
  maxPersons: any;
  price: any;

  constructor(private formBuilder: FormBuilder,
    private cottageService: CottageService,
    private sanitizer : DomSanitizer,
    private route: ActivatedRoute,
    private router: Router,
    private additionalService: AdditionalServicesService,
    private appointmentService: AppointmentService) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.additionalService.findAdditionalServices(this.id).subscribe((data : any) => {
      this.additionalServices = data;
      console.log(this.additionalServices)
    });
  }
  
  
  cancel(){
    this.router.navigate(['/cottage-details/' + this.id]);
  }

  selected(id, $event) {
     if ($event.target.checked === true) {
        this.selectedServices.push(id);
        console.log("Svi izabrani: " + this.selectedServices)
      }
    else if ($event.target.checked === false) {
        this.selectedServices = this.selectedServices.filter(item => item !== id);
        console.log("Svi izabrani: " + this.selectedServices)
      }

      console.log(this.startDate)

 }

 selectStartDate($event){
   console.log(this.startDate)
 }

 submit(){
  // console.log(this.cottage.bedsPerRoom)
  // this.cottageService.editBasicInfo(this.cottage).subscribe((data) =>{
  //   this.router.navigate(['/cottage-details/' + this.id]);
  // });
  
  var dto = {
    "id": this.id,
    "startDate": formatDate(this.startDate,'dd/MM/yyyy HH:mm','en_US'),
    "endDate": formatDate(this.endDate,'dd/MM/yyyy HH:mm','en_US'),
    "expirationDate": formatDate(this.expirationDate,'dd/MM/yyyy HH:mm','en_US'),
    "maxPersons": this.maxPersons,
    "price": this.price,
    "additionalServices": this.selectedServices
  }
  this.appointmentService.addNewAction(dto).subscribe((data) =>{
  });      
  // this.router.navigate(['/show-free-appointments/'+this.id]);
}


}

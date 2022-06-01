import { AdditionalServicesService } from './../../../service/additional-services.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppointmentService } from 'src/app/service/appointment.service';
import { DatePipe } from '@angular/common'

@Component({
  selector: 'app-show-free-appointments',
  templateUrl: './show-free-appointments.component.html',
  styleUrls: ['./show-free-appointments.component.css']
})
export class ShowFreeAppointmentsComponent implements OnInit {
  appointments = [];
  id: any;
  date: any;
  additionalServices = [];

  startDate: any;
  endDate: any;
  expirationDate: any;
  maxPersons: any;
  price: any;

  newAction = {
    "id": "",
    "startDate" : "",
    "endDate": "",
    "expirationDate": "",
    "maxPersons": "",
    "price": ""


  }

  constructor(private route: ActivatedRoute,
    private appointmentService: AppointmentService,
    public datepipe: DatePipe,
    private additionalServicesService: AdditionalServicesService
    ) { }

  ngOnInit() {

    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.appointmentService.checkExpiredActionsCottage(this.id).subscribe((data) => {  
      //window.location.reload()
    }); 


    this.appointmentService.findAppointments(this.id).subscribe((data : any) => {
      this.appointments = data;
      console.log(this.appointments)
      });


    }

    delete(id){
      console.log(id)

      this.appointmentService.deleteAppointment(id)
      .subscribe(data => {
        window.location.reload();
      });
      }

    findAdditionalService(id){
      console.log(id)

      this.additionalServicesService.findAdditionalServicesForAppointment(id).subscribe((data : any) => {
        this.additionalServices = data;
        console.log(this.additionalServices)
        });
  
    }


    addAction(){
    
    }
  
}

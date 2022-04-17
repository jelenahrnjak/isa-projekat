import { AdditionalServicesService } from './../../../service/additional-services.service';
import { DatePipe } from '@angular/common';
import { AppointmentService } from 'src/app/service/appointment.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-show-free-appointments-boat',
  templateUrl: './show-free-appointments-boat.component.html',
  styleUrls: ['./show-free-appointments-boat.component.css']
})
export class ShowFreeAppointmentsBoatComponent implements OnInit {
  appointments = [];
  id: any;
  date: any;
  additionalServices = [];

  startDate: any;
  endDate: any;
  expirationDate: any;
  maxPersons: any;
  price: any;
  constructor(private route: ActivatedRoute,
    private appointmentService: AppointmentService,
    public datepipe: DatePipe,
    private additionalServicesService: AdditionalServicesService) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.appointmentService.findAppointmentsBoat(this.id).subscribe((data : any) => {
      this.appointments = data;
      console.log(this.appointments)
      });
  }

  findAdditionalService(id){
    console.log(id)

    this.additionalServicesService.findAdditionalServicesForAppointment(id).subscribe((data : any) => {
      this.additionalServices = data;
      console.log(this.additionalServices)
      });

  }

}

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
  date: any
  constructor(private route: ActivatedRoute,
    private appointmentService: AppointmentService,
    public datepipe: DatePipe
    ) { }

  ngOnInit() {

    this.id = +this.route.snapshot.paramMap.get('id')!;


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
  
}

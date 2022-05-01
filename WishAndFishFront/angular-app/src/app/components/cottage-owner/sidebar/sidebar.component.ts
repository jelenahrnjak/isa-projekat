import { AppointmentService } from './../../../service/appointment.service';
import { RuleService } from './../../../service/rule.service';
import { AdditionalServicesService } from './../../../service/additional-services.service';
import { HttpClient } from '@angular/common/http';
import { ImageService } from './../../../service/image.service';
import { CottageService } from './../../../service/cottage.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  id: any;
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
  
}

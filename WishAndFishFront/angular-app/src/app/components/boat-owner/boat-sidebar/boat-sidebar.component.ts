import { AppointmentService } from './../../../service/appointment.service';
import { RuleService } from './../../../service/rule.service';
import { AdditionalServicesService } from './../../../service/additional-services.service';
import { ImageService } from 'src/app/service/image.service';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';
import { BoatService } from 'src/app/service/boat.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-boat-sidebar',
  templateUrl: './boat-sidebar.component.html',
  styleUrls: ['./boat-sidebar.component.css']
})
export class BoatSidebarComponent implements OnInit {
  id: any;

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
}

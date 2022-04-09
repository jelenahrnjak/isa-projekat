import { AdditionalServicesService } from './../../../service/additional-services.service';
import { Cottage } from './../../../model/cottage';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { CottageService } from './../../../service/cottage.service';
import { FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

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

  constructor(private formBuilder: FormBuilder,
    private cottageService: CottageService,
    private sanitizer : DomSanitizer,
    private route: ActivatedRoute,
    private router: Router,
    private additionalService: AdditionalServicesService) { }

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

  submit(){
    console.log(this.cottage.bedsPerRoom)
    this.cottageService.editBasicInfo(this.cottage).subscribe((data) =>{
      this.router.navigate(['/cottage-details/' + this.id]);
    });
  }

  selected(id, $event) {
     if ($event.target.checked === true) {
        console.log(id)
      }

 }
}

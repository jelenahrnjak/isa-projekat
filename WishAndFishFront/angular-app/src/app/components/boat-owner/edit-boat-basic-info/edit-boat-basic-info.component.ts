import { Boat } from 'src/app/model/boat.model';
import { BoatService } from 'src/app/service/boat.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-boat-basic-info',
  templateUrl: './edit-boat-basic-info.component.html',
  styleUrls: ['./edit-boat-basic-info.component.css']
})
export class EditBoatBasicInfoComponent implements OnInit {
  id: any = -1;
  boat : Boat = new Boat();

  constructor(private formBuilder: FormBuilder,
    private boatService: BoatService,
    private sanitizer : DomSanitizer,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;
    this.boatService.findBoat(this.id).subscribe((data) => {
      this.boat = data;
      console.log(this.boat)
    });
  }

  cancel(){
    this.router.navigate(['/boat-details/' + this.id]);
  }

  submit(){
    this.boatService.editBasicInfo(this.boat).subscribe((data) =>{
      this.router.navigate(['/boat-details/' + this.id]);
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DomSanitizer, SafeStyle } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Cottage } from 'src/app/model/cottage';
import { CottageService } from 'src/app/service/cottage.service';

@Component({
  selector: 'app-edit-cottage-basic-info',
  templateUrl: './edit-cottage-basic-info.component.html',
  styleUrls: ['./edit-cottage-basic-info.component.css']
})
export class EditCottageBasicInfoComponent implements OnInit {
  id: any = -1;
  showAddress = false;
  showFirstForm = true;
  showRules = false;
  cottage : Cottage = new Cottage();
  userImage: SafeStyle;
  image: SafeStyle;
  slika: any;
  constructor(private formBuilder: FormBuilder,
    private cottageService: CottageService,
    private sanitizer : DomSanitizer,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.cottageService.findCottage(this.id).subscribe((data) => {
      this.cottage = data;
      // this.userImage = this.sanitizer.bypassSecurityTrustStyle('url(assets/Images/' + data.coverImage +')');
      this.userImage = data.coverImage;
    });

    
  }

  cancel(){
    this.router.navigate(['/cottage-details/' + this.id]);
  }

  submit(){
    console.log(this.cottage)
  }

  
}

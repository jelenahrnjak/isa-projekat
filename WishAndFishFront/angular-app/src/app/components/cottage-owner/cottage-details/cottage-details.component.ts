import { CottageService } from 'src/app/service/cottage.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cottage } from 'src/app/model/cottage';
import { SafeStyle,DomSanitizer } from '@angular/platform-browser';
@Component({
  selector: 'app-cottage-details',
  templateUrl: './cottage-details.component.html',
  styleUrls: ['./cottage-details.component.css']
})
export class CottageDetailsComponent implements OnInit {
  id: any = -1;
  cottage : Cottage = new Cottage();
  userImage: SafeStyle;
  image: SafeStyle;
  editBasicInfo: boolean = false;
  mainInfo: boolean = true;
  constructor(private route: ActivatedRoute,
    private cottageService: CottageService,
    private sanitizer : DomSanitizer,
    private router: Router,
    ) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.cottageService.findCottage(this.id).subscribe((data) => {
      this.cottage = data;
      console.log(this.cottage);
      this.userImage = this.sanitizer.bypassSecurityTrustStyle('url(assets/Images/' + data.coverImage +')');
      console.log(this.userImage)

    });
  }

  editInfo(){
    this.router.navigate(['/edit-cottage-basic-info/'+this.id]);
  }
  
}

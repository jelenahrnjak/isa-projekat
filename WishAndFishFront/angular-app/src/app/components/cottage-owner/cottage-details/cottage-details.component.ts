import { CottageService } from 'src/app/service/cottage.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-cottage-details',
  templateUrl: './cottage-details.component.html',
  styleUrls: ['./cottage-details.component.css']
})
export class CottageDetailsComponent implements OnInit {
  id: any = -1;
  cottage : any;
  constructor(private route: ActivatedRoute,
    private cottageService: CottageService) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.cottageService.findCottage(this.id).subscribe((data: any) => {
      this.cottage = data;
      console.log(this.cottage);
    });

   
  }



}

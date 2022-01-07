import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { AuthService, UserService } from '../../../service';

import { Subject } from 'rxjs/Subject';
import { CottageService } from 'src/app/service/cottage.service';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-cottage',
  templateUrl: './cottage.component.html',
  styleUrls: ['./cottage.component.css']
})
export class CottageComponent implements OnInit {
  returnUrl: string;
  notification: DisplayMessage; 
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  cottages: any 
  constructor( private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private cottageService: CottageService) { }

  ngOnInit() {
     this.route.params
     .pipe(takeUntil(this.ngUnsubscribe))
     .subscribe((params: DisplayMessage) => {
       this.notification = params;
   }); 
  this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  this.cottageService.getAll().subscribe((data : any) => {
    this.cottages = data;
  }); 

  console.log(this.cottages)
  }

}

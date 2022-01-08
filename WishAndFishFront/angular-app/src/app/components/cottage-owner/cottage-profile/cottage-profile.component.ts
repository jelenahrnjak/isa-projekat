import { CottageOwnerService } from './../../../service/cottage-owner.service';
import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { AuthService, UserService } from '../../../service';
import { ActivatedRoute, Router } from '@angular/router';
import { CottageService } from 'src/app/service/cottage.service';
import { takeUntil } from 'rxjs/operators';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-cottage-profile',
  templateUrl: './cottage-profile.component.html',
  styleUrls: ['./cottage-profile.component.css']
})
export class CottageProfileComponent implements OnInit {
  returnUrl: string;
  notification: DisplayMessage; 
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  cottages: any 
  
  constructor(private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private cottageService: CottageService,
    private cottageOwnerService: CottageOwnerService) { }


  ngOnInit() {
    this.route.params
     .pipe(takeUntil(this.ngUnsubscribe))
     .subscribe((params: DisplayMessage) => {
       this.notification = params;
   }); 

   this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
   this.cottageOwnerService.getCottagesFromOwner().subscribe((data : any) => {
     this.cottages = data;
   });
 
   console.log(this.cottages)
  }

}

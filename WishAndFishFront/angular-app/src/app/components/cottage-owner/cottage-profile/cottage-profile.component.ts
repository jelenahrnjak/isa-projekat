import { CottageOwnerService } from './../../../service/cottage-owner.service';
import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { AuthService, UserService, ConfigService } from '../../../service';
import { ActivatedRoute, Router } from '@angular/router';
import { CottageService } from 'src/app/service/cottage.service';
import { takeUntil } from 'rxjs/operators';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

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
  cottages: any;
  reserved : boolean = false;
  form: FormGroup;
  searchDTO = {
    "name" : "",
    "address" : "",
    "rating" : "",
    //"description" : ""
  }
  constructor(private userService: UserService,
    private configService: ConfigService,
    private router: Router,
    private route: ActivatedRoute,
    private cottageService: CottageService,
    private cottageOwnerService: CottageOwnerService,
    private formBuilder: FormBuilder) { }


  ngOnInit() {
    this.form = this.formBuilder.group({  
      name: [''],
      address: [''], 
      //description: [''],  
      rating: ['',Validators.compose([Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])]  
    })
    
    this.route.params
     .pipe(takeUntil(this.ngUnsubscribe))
     .subscribe((params: DisplayMessage) => {
       this.notification = params;
   }); 

   this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/cottage-profile';
   this.cottageOwnerService.getCottagesFromOwner().subscribe((data : any) => {
     this.cottages = data;
     console.log(this.cottages)

   });
 
  }

  search(){
    this.searchDTO.name = this.form.get('name').value
    this.searchDTO.address = this.form.get('address').value
    this.searchDTO.rating = this.form.get('rating').value
    //this.searchDTO.description = this.form.get('description').value 
    if(this.searchDTO.name == "" && this.searchDTO.address == "" && this.searchDTO.rating == ""){
      console.log("sve prazno")
    }
    else{
      this.cottageService.search(this.searchDTO).subscribe((data : any) => { 
        this.cottages = data; 
      });
    }
 
  }

  clear(){
    this.form.setValue({"name" : "", "address" : "", "rating": ""})
    this.cottageOwnerService.getCottagesFromOwner().subscribe((data : any) => {
      this.cottages = []
      this.cottages = data;
      console.log(this.cottages)
    }); 
  }

  delete(id){
    this.cottageService.deleteCottage(id)
    .subscribe(data => {
      window.location.reload();
    },
      error => {
        this.reserved = true;
        // window.alert('The cottage is reserved!');
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'The cottage is reserved!',
        })
      });
    }

  isReserved(){
      return this.reserved;
  }

  closeModal(){
    this.reserved = false;
  }

  details(id){
    this.router.navigate(['/cottage-details/'+id]);
  }
}

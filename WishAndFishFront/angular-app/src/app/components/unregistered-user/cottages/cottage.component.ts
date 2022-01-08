import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { AuthService, UserService } from '../../../service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
  form: FormGroup;
  
  searchDTO = {
    "name" : "",
    "address" : "",
    "rating" : "",
   // "price" : "",
    "description" : ""
  }

  constructor(  
    private route: ActivatedRoute,
    private cottageService: CottageService,   
    private formBuilder: FormBuilder) { }

  ngOnInit() {
    
    this.form = this.formBuilder.group({  
      name: [''],
      address: [''], 
      description: [''],  
      rating: ['',Validators.compose([Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])]  
    })
     this.route.params
     .pipe(takeUntil(this.ngUnsubscribe))
     .subscribe((params: DisplayMessage) => {
       this.notification = params;
   }); 
  this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  this.cottageService.getAll().subscribe((data : any) => {
    this.cottages = data;
  }); 
 
  }

  search(){
    this.searchDTO.name = this.form.get('name').value
    this.searchDTO.address = this.form.get('address').value
    this.searchDTO.rating = this.form.get('rating').value
    //this.searchDTO.price = this.form.get('price').value
    this.searchDTO.description = this.form.get('description').value 
    this.cottageService.search(this.searchDTO).subscribe((data : any) => { 
      this.cottages = data; 
    }); 
  }

  clear(){
    this.form.setValue({"name" : "", "address" : "", "rating": "", "description" : ""})
    this.cottageService.getAll().subscribe((data : any) => {
      this.cottages = data;
    }); 
  }
}

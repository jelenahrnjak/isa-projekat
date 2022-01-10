import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { AuthService, UserService } from '../../../service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs/Subject';
import { AdventureService } from 'src/app/service/adventure.service';

@Component({
  selector: 'app-instructors',
  templateUrl: './instructors.component.html',
  styleUrls: ['./instructors.component.css']
})
export class InstructorsComponent implements OnInit {

  adventures:any= []
  form: FormGroup;
  searchDTO = {
    "name" : "",
    "address" : "",
    "rating" : "",
    "price" : "", 
    "instructor" : ""
  }

  constructor( 
    private adventureService: AdventureService,   
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({  
      name: [''],
      address: [''], 
      instructor: [''],  
      rating: ['',Validators.compose([Validators.min(0), Validators.max(5), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])], 
      price: ['',Validators.compose([Validators.min(0), Validators.pattern('([0-9]+\.?[0-9]*|\.[0-9]+)$')])]  ,
 
    })
    this.adventureService.getAll().subscribe((data : any) => {
      this.adventures = data;
  }); 
  }

  search(){
    this.searchDTO.name = this.form.get('name').value
    this.searchDTO.address = this.form.get('address').value
    this.searchDTO.rating = this.form.get('rating').value
    this.searchDTO.price = this.form.get('price').value 
    this.searchDTO.instructor = this.form.get('instructor').value
    this.adventureService.search(this.searchDTO).subscribe((data : any) => { 
      this.adventures = data; 
    });
  }

  clear(){
    this.form.setValue({"name" : "", "address" : "", "rating": "",   "price" : "","instructor":""})
    this.adventureService.getAll().subscribe((data : any) => {
      this.adventures = data;
    }); 
  }
}

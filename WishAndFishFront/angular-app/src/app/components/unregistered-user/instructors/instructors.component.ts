import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-instructors',
  templateUrl: './instructors.component.html',
  styleUrls: ['./instructors.component.css']
})
export class InstructorsComponent implements OnInit {

  instructors:any= []
  constructor() { }
  form: FormGroup;

  ngOnInit() {
  }

  search(){

  }

  clear(){

  }
}

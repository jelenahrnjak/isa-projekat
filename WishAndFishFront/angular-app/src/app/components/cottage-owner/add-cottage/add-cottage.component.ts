import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-cottage',
  templateUrl: './add-cottage.component.html',
  styleUrls: ['./add-cottage.component.css']
})
export class AddCottageComponent implements OnInit {
  showAddress = false;
  showFirstForm = true;
  showRules = false;
  constructor() { }

  ngOnInit() {
  }

  
  showAddressForm() {
    this.showAddress = true;
  }

  showMainForm() {
    this.showAddress = false;
  }

  
  showRulesForm() {
    this.showRules = true;
  }

}

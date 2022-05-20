import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DomSanitizer, SafeStyle } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Cottage } from 'src/app/model/cottage';
import { CottageService } from 'src/app/service/cottage.service';
import Swal from 'sweetalert2';

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
  invalid : boolean = false;

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
    var letters = /[a-zA-Z]/;

    if(this.cottage.name == "" || this.cottage.description == "" ||this.cottage.pricePerDay== "" || this.cottage.numberOfRooms== "" || this.cottage.bedsPerRoom== "" || this.cottage.address.street == "" || this.cottage.address.streetNumber == "" || this.cottage.address.postalCode == "" || this.cottage.address.longitude == "" || this.cottage.address.latitude == "" || this.cottage.address.cityName == "" || this.cottage.address.countryName == ""){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Fill all fields!',
      })    
    }
    else if(letters.test(this.cottage.pricePerDay) || letters.test(this.cottage.bedsPerRoom)|| letters.test(this.cottage.numberOfRooms) ||letters.test(this.cottage.address.latitude) || letters.test(this.cottage.address.longitude)){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Fill in the fields correctly, some of them should not contain letters!',
      })  
    }
    else{
      this.cottageService.editBasicInfo(this.cottage).subscribe((data) =>{
        this.router.navigate(['/cottage-details/' + this.id]);
      });
    }

  }

  
}

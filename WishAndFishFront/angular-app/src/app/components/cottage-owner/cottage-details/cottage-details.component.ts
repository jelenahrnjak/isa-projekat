import { RuleService } from './../../../service/rule.service';
import { AdditionalServicesService } from './../../../service/additional-services.service';
import { ImageService } from 'src/app/service/image.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CottageService } from 'src/app/service/cottage.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cottage } from 'src/app/model/cottage';
import { SafeStyle,DomSanitizer } from '@angular/platform-browser';
import Swal from 'sweetalert2';
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
  selectedFile = null;

  imageDto = {
    "path": "",
    "cottageId": ""
  };

  newAdditionalService = {
    "id": "",
    "name" : "",
    "price" : ""
  }

  additionalServices : any;
  rules: any;
  images: any;
  constructor(private route: ActivatedRoute,
    private cottageService: CottageService,
    private sanitizer : DomSanitizer,
    private router: Router,
    private http : HttpClient,
    private imageService: ImageService,
    private additionalService: AdditionalServicesService,
    private ruleService : RuleService
    ) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.cottageService.findCottage(this.id).subscribe((data) => {
      this.cottage = data;
      this.userImage = this.sanitizer.bypassSecurityTrustStyle('url(assets/Images/' + data.coverImage +')');
      console.log(this.cottage)
    });

    this.imageService.findImages(this.id).subscribe((data : any) => {
      this.images = data;
        console.log(this.images)
      });

    this.additionalService.findAdditionalServices(this.id).subscribe((data : any) => {
      this.additionalServices = data;
      console.log(this.additionalServices[0])
    });

     this.ruleService.findRules(this.id).subscribe((data : any) => {
      this.rules = data;
        console.log(this.rules)
      });
  }

  

  editInfo(){
    this.router.navigate(['/edit-cottage-basic-info/'+this.id]);
  }

  selectImage(event){
    this.selectedFile = event.target.files[0].name;
  }

  addImage(){
    this.imageDto.path = this.selectedFile;
    this.imageDto.cottageId = this.id;
    this.imageService.addImage(this.imageDto).subscribe(() =>{

    });      
    window.location.reload();

  }

  deleteServices(id){
    console.log(id)

    this.additionalService.deleteAdditionalService(id)
    .subscribe(data => {
      window.location.reload();
    });
    }
  

    deleteRule(id){
      console.log(id)
  
      this.ruleService.deleteRule(id)
      .subscribe(data => {
        window.location.reload();
      });
      }

    deleteImage(path){
      console.log(path)

      this.imageService.deleteImage(path)
      .subscribe(data => {
        console.log(data)
        
        window.location.reload();
      });
      }
  
  addService(){

    var letters = /[a-zA-Z]/;

    if(letters.test(this.newAdditionalService.price) || this.newAdditionalService.name.length == 0 || this.newAdditionalService.price.length == 0){
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Incorrectly filled fields!',
      })    }
    else{
      this.newAdditionalService.id = this.id;
      this.additionalService.addService(this.newAdditionalService).subscribe(() =>{
      });      
      window.location.reload();
      this.newAdditionalService.name = ""
      this.newAdditionalService.price = ""
    }
  }
  
}

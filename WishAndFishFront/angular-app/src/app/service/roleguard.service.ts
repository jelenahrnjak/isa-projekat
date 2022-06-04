import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import { AuthguardService } from '././authguard.service';

@Injectable({
  providedIn: 'root'
})
export class RoleguardService {

  constructor(public authguardService : AuthguardService, public router: Router) {}
  canActivate(route: ActivatedRouteSnapshot): boolean {

    const expectedRole = route.data['expectedRole'];
    const token = localStorage.getItem('jwt');
    let currentRole;
    
    if (token != null){
      let jwtData = token.split('.')[1]
      let decodedJwtJsonData = window.atob(jwtData)
      let decodedJwtData = JSON.parse(decodedJwtJsonData)

      currentRole = localStorage.getItem('role')
      console.log("rola je "+ currentRole)
    }

    if (!this.authguardService.gettoken() || !currentRole.includes(expectedRole)) {
      let role =  localStorage.getItem('role')

      console.log("u roleguradu sam")

      if(role == "ROLE_CLIENT"){

        this.router.navigate(['/client']);

      }else if(role == "ROLE_COTTAGE_OWNER"){

        this.router.navigate(['/cottage-profile']);

      }else if(role == "ROLE_BOAT_OWNER"){

        this.router.navigate(['/my-boats']);

      }else{ 

        this.router.navigate(['/']);
      }
      
      return false;
    }
    return true;
  }
  
}

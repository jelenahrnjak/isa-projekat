import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { ApiService } from './api.service';
import { UserService } from './user.service';
import { ConfigService } from './config.service';
import { catchError, map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { of } from 'rxjs/internal/observable/of';
import { Observable } from 'rxjs';
import { _throw } from 'rxjs/observable/throw';
import jwt_decode from "jwt-decode";

@Injectable()
export class AuthService {

  constructor(
    private apiService: ApiService,
    private userService: UserService,
    private config: ConfigService,
    private router: Router
  ) {
  }
  logged: Boolean = false;

  private access_token = null;

  login(user) {
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    // const body = `username=${user.username}&password=${user.password}`;
    const body = {
      'email': user.username,
      'password': user.password
    };
    console.log(body)
    return this.apiService.post(this.config.login_url, JSON.stringify(body), loginHeaders)
      .pipe(map((res) => {
        this.logged = true;
        this.access_token = res.accessToken;
        let decoded: any = jwt_decode(res.accessToken)
        console.dir(res)
        localStorage.setItem("user", decoded.sub)
        localStorage.setItem("role", decoded.role)
        localStorage.setItem("jwt", res.accessToken);
        localStorage.setItem("refreshToken", res.expiresIn); 
      }));
  }

  signup(user) {
    const signupHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    console.log(user)
    return this.apiService.post(this.config.signup_url,JSON.stringify(user))
    .pipe(map(() => {
      console.log('Sign up success');
    }));
  }

  logout() { 
    this.access_token = null;
    localStorage.clear();
    this.logged = false;
    this.router.navigate(['/login']);
  }

  tokenIsPresent() {
    return this.access_token != undefined && this.access_token != null;
  }

  getToken() {
    return this.access_token;
  }


  
  verifyAccount(token) {
    return this.apiService.get(this.config.verifyAccount_url + `/${token}`, token)
  }

}

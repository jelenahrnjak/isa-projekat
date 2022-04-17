import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations'; 
import { HomeComponent } from './components/unregistered-user/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { UserMenuComponent } from './components/user-menu/user-menu.component';
import { LoginComponent } from './components/login/login.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';

import {AngularMaterialModule} from './angular-material/angular-material.module'; 

import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {ApiService} from './service/api.service'; 
import {AuthService} from './service/auth.service';
import {UserService} from './service/user.service';
import {ConfigService} from './service/config.service';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { CottageOwnerHomepageComponent } from './components/cottage-owner/cottage-owner-homepage/cottage-owner-homepage.component';
import { AcceptRegistrationComponent } from './components/accept-registration/accept-registration.component';
import { CottageComponent } from './components/unregistered-user/cottages/cottage.component';
import { CottageProfileComponent } from './components/cottage-owner/cottage-profile/cottage-profile.component';
import { AddCottageComponent } from './components/cottage-owner/add-cottage/add-cottage.component'; 
import { AdminSignUpComponent } from './components/admin-sign-up/admin-sign-up.component'; 
import { DialogContentExampleDialog } from './components/accept-registration/accept-registration.component';
import { BoatsComponent } from './components/unregistered-user/boats/boats.component';
import { InstructorsComponent } from './components/unregistered-user/instructors/instructors.component';
import { MyBoatsComponent } from './components/boat-owner/my-boats/my-boats.component';
import { AddBoatComponent } from './components/boat-owner/add-boat/add-boat.component';
import { ClientHomeComponent } from './components/client/client-home/client-home.component';
import { HistoryMenuComponent } from './components/client/history-menu/history-menu.component';
import { OfferMenuComponent } from './components/client/offer-menu/offer-menu.component';
import { CottageDetailsComponent } from './components/cottage-owner/cottage-details/cottage-details.component';
import { CottageOptionsMenuComponent } from './components/cottage-owner/cottage-options-menu/cottage-options-menu.component';
import { EditCottageBasicInfoComponent } from './components/cottage-owner/edit-cottage-basic-info/edit-cottage-basic-info.component';
import { ShowFreeAppointmentsComponent } from './components/cottage-owner/show-free-appointments/show-free-appointments.component';
import { DatePipe } from '@angular/common';
import { AppoitmentSearchComponent } from './components/client/appoitment-search/appoitment-search.component';
import { AddActionComponent } from './components/cottage-owner/add-action/add-action.component';
import { SidebarComponent } from './components/cottage-owner/sidebar/sidebar.component';
import { NewReservationComponent } from './components/cottage-owner/new-reservation/new-reservation.component';
import { BoatDetailsComponent } from './components/boat-owner/boat-details/boat-details.component';
import { BoatSidebarComponent } from './components/boat-owner/boat-sidebar/boat-sidebar.component';
import { AddBoatActionComponent } from './components/boat-owner/add-boat-action/add-boat-action.component';

@NgModule({
  declarations: [
    AppComponent, 
    HomeComponent,
    HeaderComponent,
    UserMenuComponent,
    LoginComponent,
    SignUpComponent,
    UserProfileComponent,
    ChangePasswordComponent,
    CottageOwnerHomepageComponent,
    AcceptRegistrationComponent,
    CottageComponent,
    CottageProfileComponent,
    AddCottageComponent, 
    AdminSignUpComponent, 
    DialogContentExampleDialog,
    BoatsComponent,
    InstructorsComponent,
    MyBoatsComponent,
    AddBoatComponent,
    ClientHomeComponent,
    HistoryMenuComponent,
    OfferMenuComponent,
    CottageDetailsComponent,
    CottageOptionsMenuComponent,
    EditCottageBasicInfoComponent,
    ShowFreeAppointmentsComponent,
    AppoitmentSearchComponent,
    AddActionComponent,
    SidebarComponent,
    NewReservationComponent,
    BoatDetailsComponent,
    BoatSidebarComponent,
    AddBoatActionComponent, 
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    NoopAnimationsModule,
    AngularMaterialModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [ 
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }, 
    AuthService,
    ApiService,
    UserService,
    ConfigService,
    AcceptRegistrationComponent,
    DialogContentExampleDialog,
    DatePipe
  ],
  bootstrap: [AppComponent],
  entryComponents: [DialogContentExampleDialog]
})
export class AppModule { }

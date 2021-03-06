import { SuccessfullySingUpComponent } from './components/successfully-sing-up/successfully-sing-up.component';
import { BoatReportComponent } from './components/boat-owner/boat-report/boat-report.component';
import { CottageReportComponent } from './components/cottage-owner/cottage-report/cottage-report.component';
import { BoatReservationHistoryComponent } from './components/boat-owner/boat-reservation-history/boat-reservation-history.component';
import { CottageReservationHistoryComponent } from './components/cottage-owner/cottage-reservation-history/cottage-reservation-history.component';
import { EditBoatBasicInfoComponent } from './components/boat-owner/edit-boat-basic-info/edit-boat-basic-info.component';
import { ShowFreeAppointmentsBoatComponent } from './components/boat-owner/show-free-appointments-boat/show-free-appointments-boat.component';
import { AddBoatActionComponent } from './components/boat-owner/add-boat-action/add-boat-action.component';
import { AddActionComponent } from './components/cottage-owner/add-action/add-action.component';
import { EditCottageBasicInfoComponent } from './components/cottage-owner/edit-cottage-basic-info/edit-cottage-basic-info.component';
import { CottageDetailsComponent } from './components/cottage-owner/cottage-details/cottage-details.component';
import { AddBoatComponent } from './components/boat-owner/add-boat/add-boat.component';
import { MyBoatsComponent } from './components/boat-owner/my-boats/my-boats.component';
import { AddCottageComponent } from './components/cottage-owner/add-cottage/add-cottage.component';
import { CottageProfileComponent } from './components/cottage-owner/cottage-profile/cottage-profile.component';
import { CottageComponent } from './components/unregistered-user/cottages/cottage.component';
import { CottageOwnerHomepageComponent } from './components/cottage-owner/cottage-owner-homepage/cottage-owner-homepage.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ChangePasswordComponent } from './components/change-password/change-password.component';

import { HomeComponent } from './components/unregistered-user/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { AcceptRegistrationComponent } from './components/accept-registration/accept-registration.component';
import { AdminSignUpComponent } from './components/admin-sign-up/admin-sign-up.component';
import { BoatsComponent } from './components/unregistered-user/boats/boats.component';
import { InstructorsComponent } from './components/unregistered-user/instructors/instructors.component';
import { ClientHomeComponent } from './components/client/client-home/client-home.component';
import { ShowFreeAppointmentsComponent } from './components/cottage-owner/show-free-appointments/show-free-appointments.component';
import { AppoitmentSearchComponent } from './components/client/appoitment-search/appoitment-search.component';
import { SubscriptionsComponent } from './components/client/subscriptions/subscriptions.component';
import { BoatDetailsComponent } from './components/boat-owner/boat-details/boat-details.component';
import { ReservationViewComponent } from './components/client/reservation-view/reservation-view.component';
import { UpcomingReservationsComponent } from './components/client/upcoming-reservations/upcoming-reservations.component';
import { ActionViewComponent } from './components/client/action-view/action-view.component';

import { AuthentificationGuard } from './authentification.guard'
import { RoleguardService as RoleGuard } from './service/roleguard.service';

const routes: Routes = [
 
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full', 
  },
  {
    path: 'login',
    component: LoginComponent, 
  },
  {
    path: 'signup',
    component: SignUpComponent,
  },
  {
    path: 'profile',
    component: UserProfileComponent,
    canActivate:[AuthentificationGuard] //OVO DODAVATI U PATH ZA SVE PUTANJE KOJIMA MOGU SVI REGISTROVANI DA PRISTUPE
  },
  {
    path: 'changePassword',
    component: ChangePasswordComponent,
    canActivate:[AuthentificationGuard]
  },
  {
    path: 'cottage-owner',
    component: CottageOwnerHomepageComponent,
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_COTTAGE_OWNER'  
    }
  },
  {
    path: 'accept-registration',
    component: AcceptRegistrationComponent,
  },
  {
    path: 'cottages', //svi mogu
    component: CottageComponent,
  },
  {
    path: 'admin-signup',
    component : AdminSignUpComponent,
  },
  {
    path: 'cottage-profile',
    component: CottageProfileComponent,
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_COTTAGE_OWNER'  
    }
  },
  {
    path: 'add-cottage',
    component: AddCottageComponent,
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_COTTAGE_OWNER'  
    }
  },
  {
    path: 'boats',  //svi mogu
    component: BoatsComponent,

  },
  {
    path: 'fishing',  //svi mogu
    component: InstructorsComponent,
  },
  {
    path: 'my-boats',
    component: MyBoatsComponent,
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_BOAT_OWNER'  
    }
  },
  {
    path: 'add-boat',
    component: AddBoatComponent,
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_BOAT_OWNER'  
    }
  },
  {
    path:'client',
    component: ClientHomeComponent,    
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_CLIENT'   
    }
  },
  {
    path:'cottage-details/:id',
    component: CottageDetailsComponent, //svi mogu
  }
  ,
  {
    path:'edit-cottage-basic-info/:id',
    component: EditCottageBasicInfoComponent,  
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_COTTAGE_OWNER'  
    }
  },

  {
    path:'show-free-appointments/:id',
    component: ShowFreeAppointmentsComponent,
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_COTTAGE_OWNER'  
    }
  },

  {
    path:'reservation',
    component: AppoitmentSearchComponent,
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_CLIENT'  
    }
  },

  {
    path:'add-action/:id',
    component: AddActionComponent,
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_COTTAGE_OWNER'  
    }
  },

  {
    path:'subscriptions',
    component: SubscriptionsComponent,
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_CLIENT'  
    }
  },
  {
    path:'boat-details/:id',
    component: BoatDetailsComponent, //svi mogu
  },

  {
    path:'add-boat-action/:id',
    component: AddBoatActionComponent,
    canActivate: [RoleGuard],
    data: { 
      expectedRole: 'ROLE_BOAT_OWNER'  
    }
  },

  {
    path:'show-free-appointments-boat/:id',
    component: ShowFreeAppointmentsBoatComponent,
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_BOAT_OWNER'  
    }
  },

  {
    path:'edit-boat-basic-info/:id',
    component: EditBoatBasicInfoComponent,
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_BOAT_OWNER'  
    }
  },
  {
    path:'cottage-reservation-history/:id',
    component: CottageReservationHistoryComponent,
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_COTTAGE_OWNER'  
    }
  },
  {
    path:'boat-reservation-history/:id',
    component: BoatReservationHistoryComponent,
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_BOAT_OWNER'  
    }
  },
  { 
    path: 'booking-history',
    component: ReservationViewComponent,
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_CLIENT'   
    }
  },
  {
    path: 'upcoming-reservations',
    component: UpcomingReservationsComponent, 
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_CLIENT'   
    }
  },
  {
    path:'cottage-report/:id',
    component: CottageReportComponent,
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_COTTAGE_OWNER'  
    }
  },
  {
    path:'boat-report/:id',
    component: BoatReportComponent, 
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_BOAT_OWNER'  
    }
  },
  {
    path: 'actions',
    component: ActionViewComponent, 
    canActivate: [RoleGuard], 
    data: { 
      expectedRole: 'ROLE_CLIENT'   
    }
  },
  {
    path: 'successfully-sing-up/:id',
    component: SuccessfullySingUpComponent, 
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

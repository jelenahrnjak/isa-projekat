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

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
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
  },
  {
    path: 'changePassword',
    component: ChangePasswordComponent,
  },
  {
    path: 'cottage-owner',
    component: CottageOwnerHomepageComponent,
  },
  {
    path: 'accept-registration',
    component: AcceptRegistrationComponent,
  },
  {
    path: 'cottages',
    component: CottageComponent,
  },
  {
    path: 'admin-signup',
    component : AdminSignUpComponent,
  },
  {
    path: 'cottage-profile',
    component: CottageProfileComponent,
  },
  {
    path: 'add-cottage',
    component: AddCottageComponent,
  },
  {
    path: 'boats',
    component: BoatsComponent,
  },
  {
    path: 'fishing',
    component: InstructorsComponent,
  },
  {
    path: 'my-boats',
    component: MyBoatsComponent,
  },
  {
    path: 'add-boat',
    component: AddBoatComponent,
  },
  {
    path:'client',
    component: ClientHomeComponent,
  },
  {
    path:'cottage-details/:id',
    component: CottageDetailsComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './components/registration/registration.component';
//import {AngularMaterialModule} from './angular-material/angular-material.module';
import {MatToolbarModule} from '@angular/material/toolbar'; 
import {MatTableModule} from '@angular/material/table';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    MatTableModule

  ],
  providers: [],
  bootstrap: [AppComponent,RegistrationComponent]
})
export class AppModule { }

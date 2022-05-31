import { PortalModule } from '@angular/cdk/portal';
import { ApiService } from './api.service';
import { ConfigService } from './config.service';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Appointment } from '../model/appointment';
import { Reservation } from '../model/reservation.model';
import { Review } from '../model/review.model';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private apiService: ApiService,
    private config: ConfigService) { }

    getAllByCottage(id) {
      return this.apiService.get(this.config.reservation_url + `/getAllByCottage/${id}`, id)
      .pipe(map(reservations => {
        return reservations;
      }));   
    }

    getAllByBoat(id) {
      return this.apiService.get(this.config.reservation_url + `/getAllByBoat/${id}`, id)
      .pipe(map(reservations => {
        return reservations;
      }));   
    }

    getNumberofReservationMonthlyCottage(id, year) {
      var dto = {
        "id": id,
        "year": year
      }
      console.log(dto)
      return this.apiService.post(this.config.reservation_url + `/getNumberofReservationMonthlyCottage`, dto)
      .pipe(map(reservations => {
        return reservations;
      }));   
    }

    getNumberofReservationMonthlyBoat(id, year) {
      var dto = {
        "id": id,
        "year": year
      }
      console.log(dto)
      return this.apiService.post(this.config.reservation_url + `/getNumberofReservationMonthlyBoat`, dto)
      .pipe(map(reservations => {
        return reservations;
      }));   
    }

    getNumberofReservationWeeklyCottage(dto) {
      console.log(dto)
      return this.apiService.post(this.config.reservation_url + `/getNumberofReservationWeeklyCottage`, dto)
      .pipe(map(reservations => {
        return reservations;
      }));   
    }

    getNumberofReservationSpecificWeekCottage(dto){
      console.log(dto)
      return this.apiService.post(this.config.reservation_url + `/getNumberofReservationSpecificWeekCottage`, dto)
      .pipe(map(reservations => {
        return reservations;
      }));   
    }

    getNumberofReservationSpecificWeekBoat(dto){
      console.log(dto)
      return this.apiService.post(this.config.reservation_url + `/getNumberofReservationSpecificWeekBoat`, dto)
      .pipe(map(reservations => {
        return reservations;
      }));   
    }

    getNumberofReservationYearlyCottage(id) {
      return this.apiService.get(this.config.reservation_url + `/getNumberofReservationYearlyCottage/${id}`, id)
      .pipe(map(reservations => {
        return reservations;
      }));   
    }

    getNumberofReservationYearlyBoat(id) {
      return this.apiService.get(this.config.reservation_url + `/getNumberofReservationYearlyBoat/${id}`, id)
      .pipe(map(reservations => {
        return reservations;
      }));   
    }

    getIncomeInSpecificPeriod(dto) {
      return this.apiService.post(this.config.reservation_url + `/getIncomeInSpecificPeriod`, dto)
      .pipe(map(reservations => {
        return reservations;
      }));   
    }

    
    getIncomeInSpecificPeriodBoat(dto) {
      return this.apiService.post(this.config.reservation_url + `/getIncomeInSpecificPeriodBoat`, dto)
      .pipe(map(reservations => {
        return reservations;
      }));   
    }

    search(data:any){ 
      console.log(data)
      return this.apiService.get(this.config.reservation_url + `/search`, data)
      .pipe(map(reservations => {   
        return reservations;
      }));
    }

    searchCottage(data:any){ 
      console.log(data)
      return this.apiService.get(this.config.reservation_url + `/searchCottage`, data)
      .pipe(map(reservations => {   
        return reservations;
      }));
    }

    createReservation(reservation: Reservation) {
      console.log(reservation)
      return this.apiService.post(this.config.reservation_url + `/createReservation`, reservation)
      .pipe(map((data) => {
        console.log('Creating reservation success:');
      }));  
    }
    

    getBookingHistory() {

      var email = localStorage.getItem('user');
      return this.apiService.get(this.config.reservation_url + `/booking-history/${email}`) 
    
    }

    getUpcomingReservations() {

      var email = localStorage.getItem('user');
      return this.apiService.get(this.config.reservation_url + `/upcoming-reservations/${email}`) 
    
    }

    addReview(review: Review) {
      return this.apiService.post(this.config.reservation_url + `/addReview`, review)
      .pipe(map((data) => {
        console.log('Adding review success:');
      }));  
    }

    addComplaint(review: Review) {
      return this.apiService.post(this.config.reservation_url + `/addComplaint`, review)
      .pipe(map((data) => {
        console.log('Adding review success:');
      }));  
    } 

    bookAction(id : number) {

      var email = localStorage.getItem('user');

      const body = {
        "client": email,
        "action": id, 
      }

      return this.apiService.post(this.config.reservation_url + `/bookAction`, body)
      .pipe(map((data) => {
        console.log('Action booked successfully:');
      }));  
    }
    
    cancelRegistration(id : number) {

      var email = localStorage.getItem('user');

      const body = {
        "client": email,
        "action": id, 
      }

      return this.apiService.post(this.config.reservation_url + `/cancelRegistration`, body)
      .pipe(map((data) => {
        console.log('Registration canceled successfully:');
      }));  
    }
}

import { АdditionalService } from './additional-service.model';

export class Reservation {
    constructor(
        public user : string,
        public start: Date,
        public end: Date, 
        public totalPrice: number,  
        public isAction: boolean,
        public additionalServices: АdditionalService[],
        public entity : number = 0,
        public entityId : number = 0, 
      ) {}
      
}

import { АdditionalService } from './additional-service.model';

export class Appointment {
    constructor(
        public startDate: Date,
        public endDate: Date,
        public maxPersons: number,
        public price: number,
        public duration: any,
        public reserved: boolean,
        public isAction: boolean,
        public additionalServices: АdditionalService[],
        public entityId : number = 0,
        public entity : number = 0, 
      ) {}
      
}

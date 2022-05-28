export class BookingHistory{
    constructor(
        public id : number = 0, 
        public image : string,
        public start: string,
        public end: string, 
        public owner : string,
        public totalPrice: number,  
        public isAction: boolean,
        public additionalServices: string,
        public type: string,
        public name: string,
        public address: string,
        public commentedOwner : boolean,
        public commentedEntity : boolean,

      ) {}
      
}

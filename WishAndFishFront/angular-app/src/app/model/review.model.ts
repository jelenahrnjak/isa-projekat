export class Review {
    constructor(
        public reservationId : number = 0,
        public isOwner : boolean,
        public rate: number = 0, 
        public comment: string,   
      ) {}
      
}

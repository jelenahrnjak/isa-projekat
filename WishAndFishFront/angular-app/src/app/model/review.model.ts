export class Review {
    constructor(
        public reservationID : number = 0,
        public isOwner : boolean,
        public rate: number = 0, 
        public content: string,   
        public client : string,
      ) {}
      
}

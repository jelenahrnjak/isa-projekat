import { Image } from './image'; 
import { Address } from './address'; 

export class Boat {
  
    constructor(
        public id: number = 0,
        public rating: number = 0,
        public pricePerDay: string = '',
        public name: string = '',
        public address: Address = new Address(), 
        public description: string = '',
        public coverImage: string = '', 
        public images: Image[] = [], 
        public deleted: boolean = false,
        public maximumPeople:  string = '',
        public pricePerHour : number = 0,
        public isSubscribed : boolean = false,
        public type : string = "",
        public cancellationConditions: string = "",
        public length: string = '',
        public engineNumber: string = '',
        public enginePower: string = '',
        public maxSpeed: string = '',
        public capacity: string = '',
        public price : number = 0,


      ) {}
 
}

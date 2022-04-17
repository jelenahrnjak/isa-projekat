import { Image } from './image'; 
import { Address } from './address'; 

export class FishingAdventure {
    constructor(
        public id: number = 0,
        public rating: number = 0,
        public pricePerDay: number = 0,
        public name: string = '',
        public address: Address = new Address(), 
        public description: string = '',
        public coverImage: string = '', 
        public images: Image[] = [], 
        public deleted: boolean = false,
        public maximumPeople:  number = 0,
        public price : number = 0,
        public instructorEmail : string = '',
        public instructor : string = '',
        public isSubscribed : boolean = false
      ) {}
}

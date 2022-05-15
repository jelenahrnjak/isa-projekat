import { Appointment } from './appointment';
import { Image } from './image';
import { Rule } from './rule';
import { Address } from './address';
import { Room } from "./room";
import { CottageOwner } from './cottage-owner';

export class Cottage {
    constructor(
        public id: number = 0,
        public rating: number = 0,
        public pricePerDay:string = '',
        public name: string = '',
        public address: Address = new Address(),
        public cottageOwner: CottageOwner = [],
        public description: string = '',
        public coverImage: string = '',
        public rules: Rule[] = [],
        public rooms: Room[] = [],
        public numberOfRatings: number = 0,
        public roomOverview: string = '',
        public images: Image[] = [],
        public appointments: Appointment[] = [],
        public deleted: boolean = false,
        public numberOfRooms:  string = '',
        public bedsPerRoom:  string = '',
        public price : number = 0,
        public maximumPeople : string = '',
        public isSubscribed : boolean = false
      ) {}
}

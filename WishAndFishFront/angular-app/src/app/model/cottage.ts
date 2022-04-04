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
        public pricePerDay: number = 0,
        public name: string = '',
        public address: Address[] = [],
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
        public numberOfRooms:  number = 0,
        public bedsPerRoom:  number = 0
      ) {}
}

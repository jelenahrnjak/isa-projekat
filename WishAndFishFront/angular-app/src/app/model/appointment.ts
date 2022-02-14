export class Appointment {
    constructor(
        public startDate: Date,
        public endDate: Date,
        public maxPersons: number,
        public price: number,
        public duration: any,
        public reserved: boolean

      ) {}
}

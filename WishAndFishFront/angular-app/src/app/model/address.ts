export class Address {
    constructor(
        public id: number = 0,
        public street: string = '',
        public streetNumber: string = '',
        public postalCode: string = '',
        public cityName: string = '',
        public countryName: string = '',
        public longitude: number = 0,
        public latitude: number = 0,

      ) {}
}


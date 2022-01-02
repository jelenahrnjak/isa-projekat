export class UserModel{
    constructor(public userName: string,
                public password: string,
                public email: string,
                public name: string,
                public surname: string,
                public phoneNumber: string,
                public deleted: boolean,
                public enabled: boolean){}
}
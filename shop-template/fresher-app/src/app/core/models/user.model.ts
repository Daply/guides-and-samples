import { Cart } from './cart.model';

export class User {
    userId: number;
    username: string;
    name: string;
    surname: string;
    cart: Cart;
    password: string;
    roles: UserRole[];
    permissions: [];

    constructor (newId?: number, newUsername?: string, 
        newName?: string, newSurname?: string, password?: string) {
            this.userId = newId;
            this.username = newUsername;
            this.name = newName;
            this.surname = newSurname;
            this.password = password;
    }

}

export class UserRole {
    role: string;
}
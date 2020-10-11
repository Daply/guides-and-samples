import { Output, Injectable, EventEmitter } from '@angular/core';
import { UserService } from './user.service';
import { LoginService } from './login.service';

@Injectable()
export class Authenticated {

    @Output() ifAuthenticated = new EventEmitter();
    private _authenticated: boolean = false;
    private _roles: string[] = [];

    constructor(private loginService: LoginService) {

    }

    get authenticated() {
        this._authenticated = this.checkIfAuthenticated();
        return this._authenticated;
    }

    get roles() {
        return this._roles;
    }

    authenticate(value: string) {
        this.setSessionStorage(value);
        this.setRoles();
    }

    setRoles() {
        this.loginService.getRoles().subscribe(rolesResponse => {
            this._roles = rolesResponse;
        })
    }

    setSessionStorage(value: string) {
        sessionStorage.setItem("X-AUTH-TOKEN", value);
    }

    auth() {
        this._authenticated = this.checkIfAuthenticated();
        this.ifAuthenticated.emit(this.authenticated);
    }

    getEmit() {
        return this.ifAuthenticated;
    }

    checkIfAuthenticated(): boolean {
        if (sessionStorage.getItem("X-AUTH-TOKEN")) {
            return true;
        }
        else {
            return false;
        }
    }

}
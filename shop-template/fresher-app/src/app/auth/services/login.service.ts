import { Injectable, EventEmitter, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Header } from 'src/app/core/services/header';
import { User } from 'src/app/core/models/user.model';
import { Status } from 'src/app/core/models/status';
import { baseUrl } from 'src/app/core/services/base-url';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  
  header: Header = new Header();
  @Output() ifAuthenticated = new EventEmitter();

  constructor(protected httpClient: HttpClient) { }

  login(user: User) {
      return this.httpClient.post<Status>(baseUrl + "/login", {
          username: user.username,
          password: user.password
      }, { observe: 'response' });
  }

  logout() {
    this.header.addAuthTokenHeader();
    this.header.cleanToken();
    return this.httpClient.post<Observable<boolean>>(baseUrl + "/logout", this.header.options);
  }

  getRoles() {
    this.header.addAuthTokenHeader();
    return this.httpClient.get<string[]>(baseUrl + "/roles", this.header.options);
  }

}

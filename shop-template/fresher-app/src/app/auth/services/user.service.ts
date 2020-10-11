import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Header } from 'src/app/core/services/header';
import { User } from 'src/app/core/models/user.model';
import { baseUrl } from 'src/app/core/services/base-url';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  header: Header = new Header();

  constructor(protected httpClient: HttpClient) { 
  }

  getCurrentUser(): Observable<User> {
    this.header.addAuthTokenHeader();
    return this.httpClient.get<User>(baseUrl + '/user', this.header.options);
 }

  getUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(baseUrl + '/users', this.header.options);
  }

  getUserById(id: number): Observable<User> {
    this.header.addAuthTokenHeader();
    return this.httpClient.get<User>(baseUrl + '/users/' + id, this.header.options);
  }

  saveUser(user: User) {
    return this.httpClient.post<Observable<boolean>>(baseUrl + '/users/' + user.userId + '/update', user);
  }

  addNewUser(user: User) {
    return this.httpClient.post<Observable<boolean>>(baseUrl + '/add/user', user);
  }

  deleteUser(user: User) {
    return this.httpClient.post<void>(baseUrl + '/users/' + user.userId + '/delete', null);
  }

}

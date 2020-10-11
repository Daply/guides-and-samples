import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Header } from 'src/app/core/services/header';
import { User } from 'src/app/core/models/user.model';
import { Status } from 'src/app/core/models/status';
import { baseUrl } from 'src/app/core/services/base-url';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  header: Header = new Header();

  constructor(protected httpClient: HttpClient) { }

  signup(user: User) {
    return this.httpClient.post<Status>(baseUrl + "/signup", user, 
    { observe: 'response' });
  }

}

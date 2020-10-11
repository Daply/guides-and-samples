import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Header } from 'src/app/core/services/header';
import { baseUrl } from 'src/app/core/services/base-url';

@Injectable({
  providedIn: 'root'
})
export class OAuthService {

  header: Header = new Header();

  constructor(private httpClient: HttpClient) { }

  auth() {
    return this.httpClient.get<any>(baseUrl + '/twitter/auth', this.header.options);
  }

}

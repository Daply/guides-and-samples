import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { baseUrl } from './base-url';
import { Header } from './header';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  header: Header = new Header();

  constructor(protected httpClient: HttpClient) { }

  addNewProduct(data) {
    return this.httpClient.post<any>(baseUrl + '/add/product', data);
  }

}

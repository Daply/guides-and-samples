import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Product } from '../models/product.model';
import { Header } from './header';
import { baseUrl } from './base-url';
import { Cart } from '../models/cart.model';
import { CookieService } from 'ngx-cookie-service';
import { Authenticated } from 'src/app/auth/services/auth.service';
import { ProductService } from './product.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  header: Header = new Header();

  constructor(protected httpClient: HttpClient,
              private auth: Authenticated,
              private cookieService: CookieService,
              private productService: ProductService) { }

  getCurrentCart(): Observable<Cart> {
    if (this.auth.authenticated) {
      this.header.addAuthTokenHeader();
      let result = this.httpClient.get<Cart>(baseUrl + '/cart', this.header.options).pipe(map( cart => {
        if (this.cookieService.get('cart')) {
            this.cookieService.delete('cart');
        }
        return cart;
      }));
      return result;
    }
    else {
      return new Observable<Cart>(observer => {
        let cartFromCookies;
        if (this.cookieService.get('cart')) {
          cartFromCookies = JSON.parse(this.cookieService.get('cart'));
          this.getImagesForCartProducts(cartFromCookies).subscribe(cart => {
            observer.next(cart);
          });
        }
        else {
          cartFromCookies = new Cart();
          cartFromCookies.products = []; 
          cartFromCookies.totalPrice = 0;
          this.cookieService.set('cart', JSON.stringify(cartFromCookies));
          observer.next(cartFromCookies);
        }
      });
    }
  }

  getImagesForCartProducts(cartFromCookies: Cart) {
    return new Observable<Cart>(observer => {
        let cartProductsIds = cartFromCookies.products.map(product => {
          if (product) {
          return product.product.productId;
          }
        });
        this.productService.getProducts(cartProductsIds).subscribe(products => {
          const map = {};
          for (let pq of cartFromCookies.products) {
            map[pq.product.productId] = pq;
          }

          for (let p of products) {
            const pq = map[p.productId];
            if (pq.product.productId === p.productId) {
                pq.product.image = p.image;
            }
          }
          observer.next(cartFromCookies);
        });
    });
  }

  getProductsByIds(cartFromCookies: Cart, cartProductsIds: Array<Number>) {
    return this.productService.getProducts(cartProductsIds).subscribe(products => {
      for (let pq of cartFromCookies.products) {
         for (let p of products) {
              if (pq.product.productId === p.productId) {
                  pq.product.image = p.image;
              }
         }
      }
      return cartFromCookies;
   });
  }

  combineCarts(cart: Cart, cartFromCookies: Cart): Cart {
    for (let productQuantity of cart.products) {
      for (let productQuantityCookies of cartFromCookies.products) {
          if (productQuantityCookies.product.productId === 
                                productQuantity.product.productId) {
              productQuantity.quantity += productQuantityCookies.quantity;
              productQuantity.price += productQuantityCookies.price;
          }
      }
    }
    return cart;
  }

  getCartProducts(cartId: number): Observable<Product[]> {
    this.header.addAuthTokenHeader();
    return this.httpClient.get<Product[]>(baseUrl + '/carts/' + cartId + '/products', this.header.options);
  }
 
  getUsersCart(userId: number): Observable<Cart> {
    return this.httpClient.get<Cart>(baseUrl + '/users/' + userId + '/cart', this.header.options);
  }

  updateCart(cart: Cart): Observable<Cart> {
    return this.httpClient.post<Cart>(baseUrl + '/cart/update', cart);
  }

}

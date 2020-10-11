import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/product.model';
import { Header } from './header';
import { baseUrl } from './base-url';
import { CookieService } from 'ngx-cookie-service';
import { Cart } from '../models/cart.model';
import { ProductCartQuantity } from '../models/product-cart-quantity.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  header: Header = new Header();

  constructor(protected httpClient: HttpClient,
              private cookieService: CookieService) { }

  getAllProductsNumber(): Observable<number> {
      return this.httpClient.get<number>(baseUrl + '/all/products/count', this.header.options);
  }

  getProductsByPage(page: number, count: number): Observable<Product[]> {
      return this.httpClient.get<Product[]>(baseUrl + `/all/products/${page}/${count}`, this.header.options);
  }

  getAllProducts(): Observable<Product[]> {
      return this.httpClient.get<Product[]>(baseUrl + '/all/products', this.header.options);
  }

  getProductById(id: number) {
      return this.httpClient.get<Product>(baseUrl + `/products/${id}`, this.header.options);
  }

  getProducts(ids: Array<Number>): Observable<Product[]> {
    let params = new HttpParams();
    if (ids.length > 0 && ids != undefined) {
      for (let id of ids) {
          params = params.append('ids', id.toString()); 
      } 
    }
    return this.httpClient.get<Product[]>(baseUrl + '/products', { params: params });
  }

  addProductToCart(cartId: number, product: Product): Observable<Product> {
      if (cartId) {
        return this.httpClient.post<Product>(baseUrl + "/carts/" + cartId + "/add/product/" + product.productId, this.header);
      }
      else {
        return new Observable<Product>(observer => {
          if (!this.cookieService.get('cart')) {
            console.log("no cart in cookies");
          }
          else {
            let cart = JSON.parse(this.cookieService.get('cart'));
            let sendProduct = new Product();
            sendProduct.productId = product.productId;
            sendProduct.name = product.name;
            sendProduct.price = product.price;
            sendProduct.description = product.description;
            cart = this.addProductToTempCart(cart, sendProduct);
            this.cookieService.set('cart', JSON.stringify(cart));
            observer.next(product);
          }
        });
      }
  }

  removeProductFromCart(cartId: number, product: Product): Observable<Product>  {
    if (cartId) {
      return this.httpClient.post<Product>(baseUrl + "/carts/" + cartId + "/remove/product/" + product.productId, this.header);
    }
    else {
      return new Observable<Product>(observer => {
        if (!this.cookieService.get('cart')) {
          console.log("no cart in cookies");
        }
        else {
          let cart = JSON.parse(this.cookieService.get('cart'));
          let sendProduct = new Product();
          sendProduct.productId = product.productId;
          sendProduct.price = product.price;
          cart = this.removeProductFromTempCart(cart, sendProduct);
          this.cookieService.set('cart', JSON.stringify(cart));
          observer.next(product);
        }
      });
    }
  }

  deleteProductFromCart(cartId: number, product: Product): Observable<Product>  {
    if (cartId) {
      return this.httpClient.post<Product>(baseUrl + "/carts/" + cartId + "/delete/product/" + product.productId, this.header);
    }
    else {
      return new Observable<Product>(observer => {
        if (!this.cookieService.get('cart')) {
          console.log("no cart in cookies");
        }
        else {
          let cart = JSON.parse(this.cookieService.get('cart'));
          let sendProduct = new Product();
          sendProduct.productId = product.productId;
          sendProduct.price = product.price;
          cart = this.deleteProductFromTempCart(cart, product);
          this.cookieService.set('cart', JSON.stringify(cart));
          observer.next(product);
        }
      });
    }
  }

  addProductToTempCart(cart: Cart, product: Product): Cart {
      let exists = false;
      for (let productCartQuantity of cart.products) {
        if (productCartQuantity.product.productId === product.productId) {
            productCartQuantity.quantity++;
            productCartQuantity.price += product.price;
            cart.totalPrice += product.price;
            exists = true;
        }
      }
      if (!exists) {
        let productCartQuantity = new ProductCartQuantity();
        productCartQuantity.product = product;
        productCartQuantity.quantity = 1;          
        productCartQuantity.price = product.price;
        cart.totalPrice += product.price;
        cart.products.push(productCartQuantity);
      }
      return cart;
  }

  removeProductFromTempCart(cart: Cart, product: Product): Cart {
    let index;
    for (index in cart.products) {
      let productCartQuantity = cart.products[index];
      if (productCartQuantity.product.productId === product.productId) {
          productCartQuantity.quantity--;
          cart.totalPrice -= product.price;
          if (productCartQuantity.quantity == 0) {
            cart.products.splice(index, 1);
          }
      }
    }       
    return cart;
  }

  deleteProductFromTempCart(cart: Cart, product: Product): Cart {
    let index;
    for (index in cart.products) {
      let productCartQuantity = cart.products[index];
      if (productCartQuantity.product.productId === product.productId) {
          cart.totalPrice -= productCartQuantity.price;
          cart.products.splice(index, 1);
      }
    }       
    return cart;
  }

  deleteProduct(productId: number): Observable<any>  {
      this.header.addAuthTokenHeader();
      return this.httpClient.get<any>(baseUrl + `/delete/product/${productId}`, this.header.options);
  }

  searchProducts(query: string) {
    if (query.trim()) {
      return this.httpClient.get<Product[]>(baseUrl + `/products/search/${query}`, this.header.options);
    }
    else {
      return this.getAllProducts();
    }
  }

}

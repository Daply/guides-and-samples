import { Component, OnInit } from '@angular/core';
import { Product } from '../core/models/product.model';
import { ProductService } from '../core/services/product.service';
import { CartService } from '../core/services/cart.service';
import { Router } from '@angular/router';
import { MatSnackBar, PageEvent } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';
import { LoginService } from '../auth/services/login.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products: Product[] = [];

  allElementsCount: number = 0;
  numberOfItemsPerPage: number = 2;
  currentPageIndex: number = 0;

  canDelete: boolean;


  constructor(private loginService: LoginService,
              private productService: ProductService,
              private snackBar: MatSnackBar,
              private sanitizer: DomSanitizer) {}

  ngOnInit() {
    this.init();
  }

  init() {
    this.checkRoles();
    this.getAllProductsNumber();
    this.loadProductsByPage();
  }

  checkRoles() {
    this.loginService.getRoles().subscribe(rolesResponse => {
      if (rolesResponse.includes("ADMIN")) {
        this.canDelete = true;
      }
    })
  }

  nextPage(pageEvent: PageEvent) {
      this.currentPageIndex = pageEvent.pageIndex;
      this.loadProductsByPage();
  }

  getAllProductsNumber() {
    this.productService.getAllProductsNumber().subscribe(count => this.allElementsCount = count);
  }

  loadProductsByPage() {
    this.productService.getProductsByPage(this.currentPageIndex*2, this.numberOfItemsPerPage).subscribe(products => {
          if (products) {
            this.products = products;
            for (let p of this.products) {
              p.picture = this.sanitizer.bypassSecurityTrustUrl('data:image/jpeg;base64,' + p.image);
            }
          }
    });
  }

  loadAllProducts() {
    this.productService.getAllProducts().subscribe(products => {
          if (products) {
            this.products = products;
            for (let p of this.products) {
              p.picture = this.sanitizer.bypassSecurityTrustUrl('data:image/jpeg;base64,' + p.image);
            }
        }
    });
  }

  productEvent(event) {
      let product: Product;
      let status: string;
      product = event.product;
      status = event.status;
      this.snackBarEvent(product.name, status);
      this.loadProductsByPage();
  }

  snackBarEvent(productName: string, status: string) {
    if (status === 'added') {
      this.snackBar.open('Product ' + productName + ' added to cart');
    }
    if (status === 'deleted') {
      this.snackBar.open('Product ' + productName + ' deleted');
    }
  }

}

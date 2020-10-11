import { Component, OnInit, Input } from '@angular/core';
import { Product } from 'src/app/core/models/product.model';
import { ProductService } from 'src/app/core/services/product.service';
import { CartService } from 'src/app/core/services/cart.service';
import { MatSnackBar } from '@angular/material';
import { LoginService } from 'src/app/auth/services/login.service';

@Component({
  selector: 'app-product-search-result',
  templateUrl: './product-search-result.component.html',
  styleUrls: ['./product-search-result.component.css']
})
export class ProductSearchResultComponent implements OnInit {

  @Input() products: Product[] = [];
  @Input() loading: boolean;
  @Input() error: any;

  canDelete: boolean;

  constructor(private loginService: LoginService,
              protected productService: ProductService,
              protected cartService: CartService,
              private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.checkRoles();
  }

  checkRoles() {
    this.loginService.getRoles().subscribe(rolesResponse => {
      if (rolesResponse.includes("ADMIN")) {
        this.canDelete = true;
      }
    })
  }

  productEvent(event) {
    let product: Product;
    let status: string;
    product = event.product;
    status = event.status;
    this.snackBarEvent(product.name, status);
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

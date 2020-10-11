import { Component, OnInit, ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { Cart } from '../core/models/cart.model';
import { CartService } from '../core/services/cart.service';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Authenticated } from '../auth/services/auth.service';
import { ProductCartQuantity } from '../core/models/product-cart-quantity.model';
import { Product } from '../core/models/product.model';
import { ProductService } from '../core/services/product.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cart: Cart;
  productCartQuantities: ProductCartQuantity[];


  constructor(private router: Router,
              private sanitizer: DomSanitizer,
              protected cartService: CartService,
              protected productService: ProductService) { }

  ngOnInit() {
    this.cart = new Cart();
    this.updateCart();
  }

  updateCart() {
    this.cartService.getCurrentCart().subscribe(cart => {
        if (cart) {
            this.cart = cart;
            this.productCartQuantities = cart.products;
            for (let p of this.productCartQuantities) {
              p.product.picture = this.sanitizer.bypassSecurityTrustUrl('data:image/jpeg;base64,' + p.product.image);
            }
        }
        else {
            this.router.navigate(['/home']);
        }
    });
  }

  trackUpdates(index, item) {
      return item.productQuantityId;
  }

  detectChanges() {
    this.updateCart();
  }

  pay() {
    this.cartService.updateCart(this.cart).subscribe(cart => {
       if (cart) {
         this.cart = cart;
       }
    });
  }

  add(product: Product) {
    this.productService.addProductToCart(this.cart.cartId, product)
                                  .subscribe(result => {
        if (result) {
          this.updateCart();
        }
    });
  }

  remove(product: Product) {
    this.productService.removeProductFromCart(this.cart.cartId, product)
                                  .subscribe(result => {
        if (result) {
          this.updateCart();
        }
    });
  }

  delete(product: Product) {
    this.productService.deleteProductFromCart(this.cart.cartId, product)
                                  .subscribe(result => {
        if (result) {
          this.updateCart();
        }
    });
  }

}

import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Product } from '../core/models/product.model';
import { Router } from '@angular/router';
import { CartService } from '../core/services/cart.service';
import { ProductService } from '../core/services/product.service';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {

  @Input() product: Product;
  @Input() canDelete: boolean;
  @Output() productEvent = new EventEmitter();

  constructor(private router: Router,
              private cartService: CartService,
              private productService: ProductService) { }

  ngOnInit() {
  }

  deleteProduct(product: Product) {
    this.productService.deleteProduct(product.productId).subscribe(response => {
          if (response) {
            this.productEvent.emit({product, status: 'deleted'});
          }
    });
  }

  add(product: Product) {
      this.cartService.getCurrentCart().subscribe(cart => {
        if (cart) {
            this.productService.addProductToCart(cart.cartId, product).subscribe(result => {
                if (result) {
                    this.productEvent.emit({product, status: 'added'});
                }
              });
          }
          else {
            this.router.navigate(['/home']);
          }
      });
  }
}

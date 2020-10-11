import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ProductCartQuantity } from '../core/models/product-cart-quantity.model';
import { ProductService } from '../core/services/product.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Product } from '../core/models/product.model';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  product: Product;

  constructor(private route: ActivatedRoute, 
              protected productService: ProductService) { }

  ngOnInit() {
    this.product = new Product();
    this.route.paramMap.subscribe(params => {
       this.product.productId = +params.get("productId");
       this.updateProduct();
    });
  }

  updateProduct() {
      this.productService.getProductById(this.product.productId).subscribe(product => {
          if (product) {
            this.product = product;
          }
      });
  }

}

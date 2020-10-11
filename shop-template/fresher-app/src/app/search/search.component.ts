import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../core/models/product.model';
import { Store } from '@ngrx/store';
import { RootStoreState, ProductStoreSelectors, ProductStoreActions } from '../root-store';
import { ProductService } from '../core/services/product.service';
import { CartService } from '../core/services/cart.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-search',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  searchQuery$: Observable<string>;
  products$: Observable<Product[]>;
  loading$: Observable<boolean>;
  error$: Observable<any>;

  constructor(private store: Store<RootStoreState.State>,
              protected productService: ProductService,
              protected cartService: CartService,
              private snackBar: MatSnackBar) {
   }

  ngOnInit() {
    this.init();
  }

  init() {
    this.products$ = this.store.select(ProductStoreSelectors.selectAllProductItems);
    this.error$ = this.store.select(ProductStoreSelectors.selectProductError);
    this.loading$ = this.store.select(ProductStoreSelectors.selectProductIsLoading);
    this.searchQuery$ = this.store.select(ProductStoreSelectors.selectProductQuery);
  }

  searchStart(query: string) {
    this.store.dispatch(new ProductStoreActions.SearchAction(query));
  }

  add(product: Product) {
    this.cartService.getCurrentCart().subscribe(cart => {
      if (cart) {
          this.productService.addProductToCart(cart.cartId, product).subscribe(result => {
              if (result) {
                  console.log("Product added");
                  this.productAdded(product.name);
              }
            });
        }
    });
  }

  productAdded(productName: string) {
    this.snackBar.open('Product ' + productName + ' added to cart');
  }

}

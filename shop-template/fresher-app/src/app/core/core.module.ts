import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductService } from './services/product.service';
import { CartService } from './services/cart.service';
import { LoginService } from '../auth/services/login.service';


@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers: [
      //interceptors,
      ProductService,
      CartService,
      LoginService
  ]
})
export class CoreModule { }

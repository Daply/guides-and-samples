import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { ProductsStoreEffects } from './effects';
import { featureReducer } from './reducer';

@NgModule({
    imports: [
      CommonModule,
      StoreModule.forFeature('product', featureReducer),
      EffectsModule.forFeature([ProductsStoreEffects])
    ],
    providers: [ProductsStoreEffects]
  })
  export class ProductStoreModule {}
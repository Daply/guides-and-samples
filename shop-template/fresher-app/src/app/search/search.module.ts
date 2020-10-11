import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchComponent } from './search.component';
import { MatExpansionModule, 
         MatProgressSpinnerModule, 
         MatFormFieldModule, 
         MatCardModule, 
         MatPaginatorModule, 
         MatInputModule,
         MatButtonModule} from '@angular/material';
import { ProductSearchComponent } from './product-search/product-search.component';
import { ProductSearchResultComponent } from './product-search-result/product-search-result.component';

@NgModule({
  declarations: [
    SearchComponent, 
    ProductSearchComponent, 
    ProductSearchResultComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    MatExpansionModule,
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatPaginatorModule
  ]
})
export class SearchModule { }

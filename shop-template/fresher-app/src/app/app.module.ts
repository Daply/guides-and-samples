import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { CartComponent } from './cart/cart.component';
import { ProductComponent } from './product/product.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule, 
         MatToolbarModule, 
         MatIconModule, 
         MatTableModule, 
         MatCardModule, 
         MatButtonModule,
         MatTabsModule,
         MatFormFieldModule,
         MatProgressSpinnerModule, 
         MatInputModule,
         MatSnackBarModule,
         MAT_SNACK_BAR_DEFAULT_OPTIONS,
         MatSidenavModule,
         MatPaginatorModule,
         MatExpansionModule} from '@angular/material';
import { AdminComponent } from './admin/admin.component';
import { CoreModule } from './core/core.module';
import { CookieService } from 'ngx-cookie-service';
import { AuthModule } from './auth/auth.module';
import { AuthRoutingModule } from './auth/auth-routing.module';
import { SearchModule } from './search/search.module';
import { SearchRoutingModule } from './search/search-routing.module';
import { RootStoreModule } from './root-store';
import { ProductCardComponent } from './product-card/product-card.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CartComponent,
    ProductComponent,
    AdminComponent,
    ProductCardComponent
  ],
  imports: [
    CoreModule,
    AuthModule,
    AuthRoutingModule,
    SearchModule,
    SearchRoutingModule,

    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    RootStoreModule,

    MatSliderModule,
    MatToolbarModule,
    MatIconModule,
    MatTableModule,
    MatCardModule,
    MatButtonModule,
    MatTabsModule,
    MatFormFieldModule,
    MatProgressSpinnerModule,
    MatInputModule,
    MatSnackBarModule,
    MatSidenavModule,
    MatPaginatorModule,
    MatExpansionModule
  ],
  providers: [CookieService,
              {provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: {duration: 1000}}],
  bootstrap: [AppComponent]
})
export class AppModule { }

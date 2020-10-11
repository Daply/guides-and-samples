import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { Authenticated } from './services/auth.service';
import { UserComponent } from './user/user.component';
import { UserService } from './services/user.service';
import { OAuthService } from './services/oauth.service';


@NgModule({
  declarations: [
    SignupComponent,
    LoginComponent,
    UserComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [UserService,
              OAuthService,
              Authenticated, 
              CookieService],
})
export class AuthModule { }

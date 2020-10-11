import { Component, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { Authenticated } from './auth/services/auth.service';
import { LoginService } from './auth/services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'fresher-app';
  authenticated: boolean;
  isAdmin: boolean;

  constructor(private router: Router,
              private auth: Authenticated,
              private loginService: LoginService) {
     this.authenticated = this.auth.checkIfAuthenticated();
     this.auth.getEmit().subscribe(authenticated => {
        this.authenticated = authenticated;
     });
     this.checkRoles();
  }

  checkRoles() {
   this.loginService.getRoles().subscribe(rolesResponse => {
     if (rolesResponse.includes("ADMIN")) {
       this.isAdmin = true;
     }
   })
 }

  home() {
     this.router.navigate(['/home']);
  }

  admin() {
   this.router.navigate(['/admin']);
  }

  search() {
     this.router.navigate(['/search']);
  }

  logout() {
     sessionStorage.setItem("X-AUTH-TOKEN", "");
     this.authenticated = this.auth.checkIfAuthenticated();
     this.home();
  }

  userInfo() {
     this.router.navigate(['/user']);
  }

  userCart() {
     this.router.navigate(['/cart']);
  }

  login() {
     this.router.navigate(['/login']);
  }

  signup() {
     this.router.navigate(['/signup']);
  }

}

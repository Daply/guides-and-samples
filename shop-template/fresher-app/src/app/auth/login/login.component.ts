import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm, FormGroup, FormControl, Validators } from '@angular/forms';
import { User } from 'src/app/core/models/user.model';
import { Status, StatusCode } from 'src/app/core/models/status';
import { LoginService } from '../services/login.service';
import { Authenticated } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userForm: FormGroup;
  userLogin: User;

  userNotValid: boolean;
  authenticated: boolean;
  status: Status;

  constructor(private router: Router, 
              private loginService: LoginService,
              private auth: Authenticated) { }

  ngOnInit() {
    this.userLogin = new User();
    this.initForm();
    this.status = new Status();
    this.userNotValid = false;
    this.authenticated = this.auth.checkIfAuthenticated();
  }

  initForm() {
    this.userForm = new FormGroup({
      'username': new FormControl(this.userLogin.username, [
        Validators.required,
        Validators.pattern('[0-9a-zA-Z]+')
      ]),
      'password': new FormControl(this.userLogin.password, [
        Validators.required
      ])
    });
  }

  login(form: NgForm) {
    if (form.valid) {
        this.userLogin.username = form.value['username'];
        this.userLogin.password = form.value['password'];
        this.loginService.login(this.userLogin).subscribe(response => {
              this.status = response.body;
              if (this.status.statusCode === StatusCode.ENTITY_DOESNT_EXIST) {
                  this.userNotValid = true;
              }
              else {
                this.auth.authenticate(response.headers.get("X-AUTH-TOKEN"));
                this.authenticated = true;
                this.auth.auth();
                this.router.navigate(['/home']);
              }
        });
    }
  }

  logout() {
    this.authenticated = false;
    this.loginService.logout().subscribe(result =>{
         console.log("user logged off");
    });
  }

}

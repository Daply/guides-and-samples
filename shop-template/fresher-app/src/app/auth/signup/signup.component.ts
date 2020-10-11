import { Component, OnInit } from '@angular/core';
import { NgForm, FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PasswordsMatchValidator } from './password-match.directive';
import { PasswordValidator } from './password-validation.directive';
import { User } from 'src/app/core/models/user.model';
import { Status, StatusCode } from 'src/app/core/models/status';
import { SignupService } from '../services/signup.service';
import { Authenticated } from '../services/auth.service';
import { OAuthService } from '../services/oauth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  userForm: FormGroup;
  newUser: User;

  authenticated: boolean;
  status: Status;
  exists: boolean;

  constructor(private router: Router,
              private signupService: SignupService,
              private auth: Authenticated,
              private oAuthService: OAuthService) { }

  ngOnInit() {
    this.newUser = new User();
    this.initForm();
    this.status = new Status();
    this.exists = false;
  }

  initForm() {
    this.userForm = new FormGroup({
      'name': new FormControl(this.newUser.name, [
        Validators.required,
        Validators.pattern('[a-zA-Z]+')
      ]),
      'surname': new FormControl(this.newUser.surname, [
        Validators.required,
        Validators.pattern('[a-zA-Z]+')
      ]),
      'username': new FormControl(this.newUser.username, [
        Validators.required,
        Validators.pattern('[0-9a-zA-Z]+')
      ]),
      'password': new FormControl(this.newUser.password, [
        Validators.required
      ]),
      'password-repeat': new FormControl(this.newUser.password, [
        Validators.required
      ])
    }, { validators: [ new PasswordsMatchValidator().matchPasswords,
      new PasswordValidator().passwordValid ] });
  }

  get name() { return this.userForm.get('name'); }

  signup(form: NgForm) {
    if (form.valid) {
      this.newUser.name = form.value['name'];
      this.newUser.surname = form.value['surname'];
      this.newUser.username = form.value['username'];
      this.newUser.password = form.value['password'];
      this.signupService.signup(this.newUser).subscribe(response => {
            this.status = response.body;
            if (this.status.statusCode === StatusCode.ALREADY_EXISTS) {
                this.exists = true;
            }
            else {
              sessionStorage.setItem("X-AUTH-TOKEN", response.headers.get("X-AUTH-TOKEN"));
              this.authenticated = true;
              this.auth.auth();
              this.router.navigate(['/home']);
            }
      });
    }
  }

  signupWithTwitter() {
    this.oAuthService.auth().subscribe(result => {
        if (result) {
          console.log('twitter');
        }
    });
  }

}

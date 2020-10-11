import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/core/models/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user: User;

  constructor(private router: Router,
              private userService: UserService) {
  }

  ngOnInit() {
    this.user = new User();
    this.updateUser();
  }

  updateUser() {
    this.userService.getCurrentUser().subscribe(user => {
      if (user) {
          this.user = user;
      }
      else {
          this.router.navigate(['/home']);
      }
    });
  }

  saveChanges() {
    this.userService.saveUser(this.user).subscribe(result => {
        if (result != null) {
            console.log("User saved");
        }
    });
  }

}

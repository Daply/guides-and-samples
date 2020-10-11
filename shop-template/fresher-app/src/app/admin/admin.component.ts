import { Component, OnInit } from '@angular/core';
import { User } from '../core/models/user.model';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { Product } from '../core/models/product.model';
import { AdminService } from '../core/services/admin.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  productForm: FormGroup;
  newProduct: Product;

  users: User[];

  file: File;
  filename: string;

  constructor(private router: Router,
              private adminService: AdminService,
              private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.newProduct = new Product();
    this.productForm = new FormGroup({
      'name': new FormControl(this.newProduct.name, [
        Validators.required
      ]),
      'description': new FormControl(this.newProduct.description, [
        Validators.required
      ]),
      'price': new FormControl(this.newProduct.price, [
        Validators.required,
        Validators.pattern('[0-9]+')
      ])
    });
  }

  fileUpload(event) {
    if (event.target.files.length > 0) {
      this.file = event.target.files[0];
      this.filename = this.file.name.split("\\").pop();
    }
  }

  addNewProduct(form: NgForm) {
    if (this.file != null && form.valid) {
      this.newProduct.name = form.value['name'];
      this.newProduct.description = form.value['description'];
      this.newProduct.price = form.value['price'];
      const formData = new FormData();
      formData.append('name', this.newProduct.name);
      formData.append('description', this.newProduct.description);
      formData.append('price', this.newProduct.price.toString());
      formData.append('file', this.file);
      this.adminService.addNewProduct(formData).subscribe(
        (res) => {
          this.productAdded(this.newProduct.name);
          this.router.navigate(['/home']);
        }
      );
    }
  }

  productAdded(productName: string) {
    this.snackBar.open('Product ' + productName + ' saved');
  }
}

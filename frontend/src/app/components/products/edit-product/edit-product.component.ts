import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { from } from 'rxjs';
import { Product } from '../../../models/product.model';
import { ProductService } from '../../../services/product.service';
import { LoginService } from '../../../services/login.service';
import { User } from 'src/app/models/user.model';


@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  product: Product;
  //imageProduct: String;
  id: number;
  logged: boolean;
  admin: boolean;

  user:User;
  bEditDeleteVisible:boolean;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public productservice: ProductService,
    public loginService: LoginService, public productService: ProductService) {

    this.id = activatedRoute.snapshot.params['idProduct'];
  }

  ngOnInit() {
    this.getCurrentUser();
    this.getSingleProduct()
  }
  
  getSingleProduct(){
    this.productservice.getSingleProduct(this.id).subscribe(
      (product: any) => {
        this.product = product
        this.getButtonVisible();
      },
      (error: any) => console.error(error)
    );
  }

  removeProduct() {
    const okResponse = window.confirm('Do you want to remove this product?');
    if (okResponse) {
      this.productservice.removeProduct(this.product).subscribe(
        (_: any) => this.router.navigate(['/new']),
        (error: any) => console.error(error)
      );
    }
  }

  editProduct() {

    this.productservice.updateProduct(this.product).subscribe(
      (_: any) => this.router.navigate(['/new']),
      (error: any) => console.error(error)
    );
  }

  getCurrentUser(){
    this.user = this.loginService.currentUser();
  }

  getButtonVisible(){
    if(this.user.nickname===this.product.user.nickname || this.user.nickname=="SixShop"){
      this.bEditDeleteVisible = true;
    } else {
      this.bEditDeleteVisible = false;
    }
  }
}

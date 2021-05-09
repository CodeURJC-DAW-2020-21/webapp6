import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../services/product.service';
import { Product } from '../../../models/product.model';
import { Router, ActivatedRoute} from '@angular/router';
import { LoginService } from '../../../services/login.service'
import { CartService } from '../../../services/cart.service';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-single-product',
  templateUrl: './single-product.component.html',
  styleUrls: ['./single-product.component.css']
})
export class SingleProductComponent implements OnInit {

  idProduct :number;
  product:Product;
  ratingProduct: number;

  user:User;
  bAddCartVisible:boolean;
  bEditVisible:boolean;

  constructor(private router: Router, private activatedRoute : ActivatedRoute, 
    public productService:ProductService, public loginService: LoginService, private cartService:CartService) { 
    
      this.idProduct = activatedRoute.snapshot.params['idProduct'];
  }

  ngOnInit() {
    this.getCurrentUser();
    this.getSingleProduct();
  }

  getSingleProduct(){
    this.productService.getSingleProduct(this.idProduct).subscribe(
      product => {
        this.product = product;
        this.getButtonVisible();
      },
      error => console.log(error)
    );
  }
  
  addCartProduct(idProduct:number){
    this.cartService.addProductCart(idProduct).subscribe(
      product => {
        this.router.navigate(['/new/cart']);
      },
      error => console.log(error)
    )
  }

  getCurrentUser(){
    this.user = this.loginService.currentUser();
  }

  getButtonVisible(){
    if(this.user.nickname===this.product.user.nickname || this.user.nickname=="SixShop"){
      this.bAddCartVisible = false;
      this.bEditVisible = true;
    } else {
      this.bAddCartVisible = true;
      this.bEditVisible = false;
    }
  }
}
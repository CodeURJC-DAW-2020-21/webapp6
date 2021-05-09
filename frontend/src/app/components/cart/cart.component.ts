import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product.model';
import { ProductService } from '../../services/product.service';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  products:Product[] = []
  idRequest:number=0;
  totalPrice:number = 0;

  constructor(private cartService:CartService, public productService:ProductService) { }

  ngOnInit() {
    this.getCart()
  }

  getCart(){
    this.cartService.getCart().subscribe(
      products => {
        this.products = products["PRODUCTS"]
        this.totalPrice = products["TOTAL PRICE"]
        this.idRequest = products["ID REQUEST"]
      },
      error => console.log(error)
    );
  }


}

import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../services/product.service';
import { Product } from '../../../models/product.model';
import { Router, ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-single-product',
  templateUrl: './single-product.component.html',
  styleUrls: ['./single-product.component.css']
})
export class SingleProductComponent implements OnInit {

  idProduct :number;
  product:Product;
  ratingProduct: number;

  constructor(private router: Router, private activatedRoute : ActivatedRoute, public productService:ProductService) { 
    this.idProduct = activatedRoute.snapshot.params['idProduct'];
  }

  ngOnInit() {
    this.getSingleProduct();
  }

  getSingleProduct(){
    this.productService.getSingleProduct(this.idProduct).subscribe(
      product => {
        this.product = product;
        console.log(this.product.rating)
      },
      error => console.log(error)
    );
  }
}
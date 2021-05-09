import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ProductService } from '../../../services/product.service';
import { ProfileService } from '../../../services/profile.service';
import { Product } from '../../../models/product.model';

@Component({
  selector: 'app-profile-products',
  templateUrl: './profile-products.component.html',
  styleUrls: ['./profile-products.component.css']
})
export class ProfileProductsComponent implements OnInit {

  products: Product[] = [];
  imagesProducts: String[] = [];

  constructor(private router:Router, private profileService: ProfileService, private productService:ProductService) { }

  ngOnInit() {
    this.getProductsProfile();
  }

  getProductsProfile(){
    this.profileService.getProductsProfile().subscribe(
      products => {
        //this.products = products["lProducts"];
        for(let x=0;x<products["lProducts"].length;x++){
          if (products["lProducts"][x].visible===true){
            this.products.push(products["lProducts"][x]);
          }
        }
        //this.products = this.products;
        //this.getImagesProducts();
      },
      error => console.log(error)
    );
  }
}

import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../services/product.service';
import { Product } from '../../../models/product.model';

@Component({
  selector: 'app-products-rating',
  templateUrl: './products-rating.component.html',
  styleUrls: ['./products-rating.component.css']
})
export class ProductsRatingComponent implements OnInit {

  productsRating: Product[] = [];
  imagesProductsRating: String[] = [];
  ratingProductsRating: number[] = [];
  totalPageRating:number=0;
  nPageRating:number=0;
  loadMoreRatingVisible:boolean=true;

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.getProductsRatingPage();
  }

  getProductsRatingPage(){
    this.productService.getProductsRatingPage(this.nPageRating).subscribe(
      products => {
        this.totalPageRating = products["TOTAL PAGE"];
        this.nPageRating++;
        if(this.nPageRating>=this.totalPageRating){this.loadMoreRatingVisible=false}
        this.productsRating = this.productsRating.concat(products["products"])
        console.log(this.productsRating)
        this.getImagesProductsRating();
        this.getRatingProductsRating();
      },
      error => console.log(error)
    );
  }

  private getImagesProductsRating() {
    for (let x = 0; x < this.productsRating.length; x++) {
      if (this.productsRating[x].imageURL == null) {
        this.imagesProductsRating[x] = "../../../assets/img/imagenes/product/" + this.productsRating[x].productName + ".jpg";
      } else {
        this.imagesProductsRating[x] = this.productsRating[x].imageURL;
      }
    }
  }

  private getRatingProductsRating(){
    for (let x=0;x<this.productsRating.length;x++){
      this.ratingProductsRating[x]=this.productsRating[x].rating;
    }
  }

}

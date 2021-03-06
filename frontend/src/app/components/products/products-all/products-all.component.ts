import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../services/product.service';
import { Product } from '../../../models/product.model';

@Component({
  selector: 'app-products-all',
  templateUrl: './products-all.component.html',
  styleUrls: ['./products-all.component.css']
})
export class ProductsAllComponent implements OnInit {

  products: Product[] = [];
  ratingProducts: number[] = [];
  totalPage:number=0;
  nPage:number=0;
  loadMoreProductsVisible:boolean=true;

  constructor(public productService: ProductService) { }

  ngOnInit() {
    this.getProductsPage();
  }

  getProductsPage(){
    this.productService.getProductsPage(this.nPage).subscribe(
      products => {
        this.totalPage = products["TOTAL PAGE"];
        this.nPage++;
        if(this.nPage>=this.totalPage){this.loadMoreProductsVisible=false}
        this.products = this.products.concat(products["products"])
        this.getRatingProducts();
      },
      error => console.log(error)
    );
  }

  private getRatingProducts(){
    for (let x=0;x<this.products.length;x++){
      this.ratingProducts[x]=this.products[x].rating;
    }
  }

}

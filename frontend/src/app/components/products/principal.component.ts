import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.css']
})
export class PrincipalComponent implements OnInit {

  products: Product[] = [];
  imagesProducts: String[] = [];
  ratingProducts: number[] = [];

  totalPage:number=0;
  nPage:number=0;
  loadMoreVisible:boolean=true;

  constructor(private router: Router, activatedRoute:ActivatedRoute, private productService: ProductService) { }

  ngOnInit() {
    this.getProductsPage();
  }

  getProductsPage(){
    this.productService.getProductsPage(this.nPage).subscribe(
      products => {
        this.totalPage = products["TOTAL PAGE"];
        this.nPage++;
        if(this.nPage>=this.totalPage){this.loadMoreVisible=false}
        this.products = this.products.concat(products["products"])
        this.getImagesProducts();
        this.getRatingProducts();
      },
      error => console.log(error)
    );
  }

  private getImagesProducts() {
    for (let x = 0; x < this.products.length; x++) {
      if (this.products[x].imageURL == null) {
        this.imagesProducts[x] = "../../../assets/img/imagenes/product/" + this.products[x].productName + ".jpg";
      } else {
        this.imagesProducts[x] = this.products[x].imageURL;
      }
    }
  }

  private getRatingProducts(){
    for (let x=0;x<this.products.length;x++){
      this.ratingProducts[x]=this.products[x].rating;
    }
  }
}

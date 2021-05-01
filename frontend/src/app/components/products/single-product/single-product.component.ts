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
  product:Product=null;
  imageProduct: String;
  ratingProduct: number;

  constructor(private router: Router, private activatedRoute : ActivatedRoute, private productService:ProductService) { 
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
        this.getImageProduct();
      },
      error => console.log(error)
    );
  }

  private getImageProduct() {
      if (this.product.imageURL == null) {
        this.imageProduct = "../../../../assets/img/imagenes/product/" + this.product.productName + ".jpg";
      } else {
        this.imageProduct = this.product.imageURL;
      }
  }

  /*
  ngOnInit() {
    this.refresh();
  }

  private refresh(){
    this.idProduct = this.activatedRoute.snapshot.paramMap.get("idProduct");
    this.httpClient.get("https://localhost:8443/api/products/"+this.idProduct).subscribe((response)=>{
      let resSTR = JSON.stringify(response);
      let resJSON = JSON.parse(resSTR);
      this.product = resJSON;
      }, 
    error => this.handleError(error));
    
  }

  private handleError(error:any){
    console.error(error);
  }
*/
}
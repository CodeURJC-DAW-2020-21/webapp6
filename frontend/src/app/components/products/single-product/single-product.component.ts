import { Component, OnInit } from '@angular/core';
import {Product} from '../product.model';
import {HttpClient} from '@angular/common/http';
import {Router, ActivatedRoute , Params} from '@angular/router';
@Component({
  selector: 'app-single-product',
  templateUrl: './single-product.component.html',
  styleUrls: ['./single-product.component.css']
})
export class SingleProductComponent implements OnInit {
  idProduct : string;
  product:Product;
  constructor(private route : ActivatedRoute, private httpClient:HttpClient) { }

  ngOnInit() {
    this.refresh();
    //console.log(this.idProduct);
  }

  private refresh(){
    this.idProduct = this.route.snapshot.paramMap.get("idProduct");
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

}

import { Component, OnInit } from '@angular/core';
import {Product} from './product.model';
import {HttpClient} from '@angular/common/http';
@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.css']
})
export class PrincipalComponent implements OnInit {

  products:Product[] = [];
  constructor(private httpClient:HttpClient) { }

  ngOnInit() {
    this.refresh();
  }

  private refresh(){
    this.httpClient.get("https://localhost:8443/api/products/").subscribe((response)=>{
      let resSTR = JSON.stringify(response);
      let resJSON = JSON.parse(resSTR);
      this.products = resJSON.products;
      }, 
    error => this.handleError(error));
  }

  private handleError(error:any){
    console.error(error);
  }
}

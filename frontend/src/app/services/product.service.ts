import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators'

import { Product } from '../models/product.model';

const API_URL = '/api/products';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
    
    constructor(private httpClient:HttpClient) { }

    getProductsPage(nPage:number):Observable<Product[]>{
      return this.httpClient.get(API_URL+"/?page="+nPage).pipe(
        catchError(error=>this.handleError(error))
      ) as Observable<Product[]>;
    }

    getSingleProduct(idProduct:number):Observable<Product>{
      return this.httpClient.get(API_URL+"/"+idProduct).pipe(
        catchError(error=>this.handleError(error))
      ) as Observable<Product>;
    }

    getProductsRatingPage(nPage:number):Observable<Product[]>{
      return this.httpClient.get(API_URL+"/rating?page="+nPage).pipe(
        catchError(error=>this.handleError(error))
      ) as Observable<Product[]>;
    }

    getProductsCategoryPage(category:String,nPage:number):Observable<Product[]>{
      return this.httpClient.get("/api/categories/"+category+"?page="+nPage).pipe(
        catchError(error=>this.handleError(error))
      ) as Observable<Product[]>;
    }

    getImageProduct(product:Product){
      if (product.imageURL === null) {
        return "../../../assets/img/imagenes/product/" + product.productName + ".jpg";
      } else {
        return "/api/profiles/"+product.idProduct+"/image"
      }
    }

    private handleError(error: any) {
      console.log("ERROR:");
      console.error(error);
      return throwError("Server error (" + error.status + "): " + error.text())
    }
}

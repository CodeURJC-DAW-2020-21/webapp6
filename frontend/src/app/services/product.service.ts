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

    /*getProducts():Observable<Product[]>{
      return this.httpClient.get(API_URL+"/").pipe(
        catchError(error=>this.handleError(error))
      ) as Observable<Product[]>;
    }*/

    getProductsPage(nPage:number):Observable<Product[]>{
      return this.httpClient.get("/api/products/?page="+nPage).pipe(
        catchError(error=>this.handleError(error))
      ) as Observable<Product[]>;
    }

    getSingleProduct(idProduct:number):Observable<Product>{
      return this.httpClient.get("/api/products/"+idProduct).pipe(
        catchError(error=>this.handleError(error))
      ) as Observable<Product>;
    }

    getProductsRatingPage(nPage:number):Observable<Product[]>{
      return this.httpClient.get("/api/products/rating?page="+nPage).pipe(
        catchError(error=>this.handleError(error))
      ) as Observable<Product[]>;
    }

    getProductsCategoryPage(category:String,nPage:number):Observable<Product[]>{
      return this.httpClient.get("/api/categories/"+category+"?page="+nPage).pipe(
        catchError(error=>this.handleError(error))
      ) as Observable<Product[]>;
    }

    getImageProduct(idProduct:number):Observable<Blob>{
      return this.httpClient.get("/api/profiles/"+idProduct+"/image").pipe(
        catchError(error=>this.handleError(error))
      ) as Observable<Blob>;
    }


    removeProduct(product: Product) {  //cambiar dirc api
      return this.httpClient.delete(API_URL + product.idProduct).pipe(
        catchError(error => this.handleError(error))
      );
    }
  
    updateProduct(product: Product) {
      return this.httpClient.put(API_URL + product.idProduct, product).pipe(
        catchError(error => this.handleError(error))
      );
    }

    private handleError(error: any) {
      console.log("ERROR:");
      console.error(error);
      return throwError("Server error (" + error.status + "): " + error.text())
    }
}

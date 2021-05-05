import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators'

import { Product } from '../models/product.model';
import { Request } from '../models/request.model'

const API_URL = '/api/profiles/';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private httpClient: HttpClient) { }

  getProductsProfile():Observable<Product[]>{
    return this.httpClient.get(API_URL).pipe(
      catchError(error=>this.handleError(error))
    ) as Observable<Product[]>;
  }

  getImageProduct(idProduct: number): Observable<Blob> {
    return this.httpClient.get(API_URL + idProduct + "/image").pipe(
      catchError(error => this.handleError(error))
    ) as Observable<Blob>;
  }

  addNewProduct(product:Product):Observable<Product>{
    return this.httpClient.post(API_URL+"products/",product).pipe(
      catchError(error=>this.handleError(error))
    ) as Observable<Product>
  }

  addImageNewProduct(idProduct:number,formData:FormData){
    return this.httpClient.post(API_URL+idProduct+"/image",formData).pipe(
      catchError(error=>this.handleError(error))
    );
  }

  getBoughtProducts():Observable<Request[]>{
    return this.httpClient.get(API_URL+"shopping").pipe(
      catchError(error=>this.handleError(error))
    ) as Observable<Request[]>
  }

  ratingProduct(idRequestDetail:number,idProduct:number,rating:number){
    return this.httpClient.put(API_URL+idRequestDetail+"/"+idProduct+"/"+rating,null).pipe(
      catchError(error=>this.handleError(error))
    )
  }

  getSales():Observable<String[]>{
    return this.httpClient.get(API_URL+"sales/").pipe(
      catchError(error=>this.handleError(error))
    ) as Observable<String[]>
  }

  private handleError(error: any) {
    console.log("ERROR:");
    console.error(error);
    return throwError("Server error (" + error.status + "): " + error.text())
  }

}

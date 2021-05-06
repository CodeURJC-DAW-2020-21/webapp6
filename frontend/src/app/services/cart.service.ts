import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators'

import { Product } from '../models/product.model';
import { id } from '@swimlane/ngx-charts';

const API_URL = '/api/carts/';

@Injectable({
  providedIn: 'root'
})
export class CartService {
    constructor(private httpClient:HttpClient) { }


    getCart():Observable<Product[]>{
        return this.httpClient.get(API_URL).pipe(
            catchError(error=>this.handleError(error))
        ) as Observable<Product[]>;
    }

    addProductCart(idProduct:number):Observable<Product>{
        return this.httpClient.post(API_URL+idProduct,null).pipe(
            catchError(error=>this.handleError(error))
        ) as Observable<Product>;
    }

    paid(idRequest:number){
        return this.httpClient.post(API_URL+"cardPayment/"+idRequest,null).pipe(
            catchError(error=>this.handleError(error))
        )
    }

    private handleError(error: any) {
        console.log("ERROR:");
        console.error(error);
        return throwError("Server error (" + error.status + "): " + error.text())
      }
}
import { Product } from "./product.model";
import { Request } from "./request.model";

export interface RequestDetail {
    idRequestDetail?:number;
    productPrice:number;
    rating:number;

    request:Request;
    product:Product;
}
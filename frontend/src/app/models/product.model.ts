import { User } from "./user.model";

export interface Product {
    idProduct?:number;
    productName:String;
    description:String;
    category:String;
    price:number;
    rating:number;
    visible:boolean;
    imageFile:Blob;
    image:boolean;
    imageURL:String;

    user?:User;
}

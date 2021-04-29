export interface Product{
    idProduct:number;
    idUser:number;
    productName:string;
    description:string;
    category:string;
    price:number;
    rating:number;
    visible:boolean;
    imageFile:Blob;
    image:boolean;
    imageURL:string;
}
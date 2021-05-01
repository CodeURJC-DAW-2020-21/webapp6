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

    /*
    private idProduct:number;
    private productName:String;
    private description:String;
    private category:String;
    private price:number;
    private rating:number;
    private visible:boolean;
    private imageFile:Blob;
    private image:boolean;
    private imageURL:String;

    
    constructor(productName:String,description:String,category:String,price:number){
        this.productName=productName;
        this.description=description;
        this.category=category;
        this.price=price;
    }*/


}

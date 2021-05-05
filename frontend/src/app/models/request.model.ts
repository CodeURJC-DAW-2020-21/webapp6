import { User } from "./user.model";
import { RequestDetail } from "./requestDetail.model";

export interface Request {
    idRequest?:number;
    date:Date;
    status:String;
    totalPrice?:String;
    
    lRequestDetail:RequestDetail[];
    buyerUser:User;
}
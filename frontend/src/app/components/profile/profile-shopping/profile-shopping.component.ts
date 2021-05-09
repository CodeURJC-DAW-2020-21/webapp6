import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';

import { Request } from '../../../models/request.model';
import { ProfileService } from '../../../services/profile.service';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'app-profile-shopping',
  templateUrl: './profile-shopping.component.html',
  styleUrls: ['./profile-shopping.component.css']
})
export class ProfileShoppingComponent implements OnInit {

  user:User;
  requests:Request[] = [];
  totalPrice:number[] = [];

  //requestDetail:RequestDetail[];

  constructor(private router:Router,private profileService:ProfileService,private loginService:LoginService) { }

  ngOnInit() {
    this.getBoughtProducts()
  }

  getBoughtProducts(){
      this.profileService.getBoughtProducts().subscribe(
        requests => {
          this.requests = requests
          for (let i=0;i<this.requests.length;i++){
            let aux=0;
            for (let x=0;x<this.requests[i].lRequestDetail.length;x++){
              aux+=this.requests[i].lRequestDetail[x].productPrice;
            }
            this.totalPrice.push(aux);
          }
        },
        error => console.log(error)
      );
  }

  public ratingProduct(idRequestDetail:number,idProduct:number,rating:number){
    this.profileService.ratingProduct(idRequestDetail,idProduct,rating).subscribe(
      _ => {
        this.getBoughtProducts()
      },
      error => console.log(error)
    );

  }

}

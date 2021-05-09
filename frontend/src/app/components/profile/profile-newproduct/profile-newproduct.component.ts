import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/models/product.model';
import { ProfileService } from 'src/app/services/profile.service';
import { LoginService } from 'src/app/services/login.service';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-profile-newproduct',
  templateUrl: './profile-newproduct.component.html',
  styleUrls: ['./profile-newproduct.component.css']
})
export class ProfileNewproductComponent implements OnInit {

  user:User;
  product:Product;

  @ViewChild("file")
  file:any

  constructor(private router:Router,activatedRoute: ActivatedRoute,private profileService:ProfileService,private loginService:LoginService) { }

  ngOnInit() {
    this.product = {productName:'',description:'',
        category:'',price:0, rating:-1,visible:true,
        imageFile:null,image:true,imageURL:null}
  }

  addNewProduct(){
    //console.log(this.product);
    this.user = this.loginService.currentUser();
    this.product.user = this.user;

    this.profileService.addNewProduct(this.product).subscribe(
      (product:Product)=>{
        this.product = product;

        const image = this.file.nativeElement.files[0];
        let formData = new FormData();
        formData.append("imageFile",image);
        
        this.profileService.addImageNewProduct(this.product.idProduct,formData).subscribe(
          _ => this.router.navigate(['/new']),
          error=>alert('Error add image to new product: '+ error)
        );
      },
      error=>alert('Error creating new product: '+ error)
    );
  }
}

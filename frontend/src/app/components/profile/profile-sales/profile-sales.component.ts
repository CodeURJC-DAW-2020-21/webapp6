import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProfileService } from '../../../services/profile.service';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'app-profile-sales',
  templateUrl: './profile-sales.component.html',
  styleUrls: ['./profile-sales.component.css']
})
export class ProfileSalesComponent implements OnInit {

  sales:String[];

  constructor(private router:Router,private profileService:ProfileService) { }

  ngOnInit() {
    this.getSales()
  }

  getSales(){
    this.profileService.getSales().subscribe(
      sales => {
        this.sales = sales;
      },
      error => console.log(error)
    );
  }
}

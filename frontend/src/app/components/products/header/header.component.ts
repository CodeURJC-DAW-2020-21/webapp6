import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../../../services/login.service';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  profileWindow:boolean;
  user:User;

  constructor(private router: Router, activatedRoute:ActivatedRoute, private userService: UserService, public loginService:LoginService) {}

  
  ngOnInit() {
    this.currentUser();
  }

  ngDoCheck(){
    this.currentUser();
    if(this.router.url === ('/new/profile') || this.router.url === ('/new/profile/profile-newproduct') ||
        this.router.url === ('/new/profile/profile-sales') || this.router.url === ('/new/profile/profile-shopping')) {
      this.profileWindow=true
    }
    else this.profileWindow=false
  }

  logOut(){
    this.loginService.logOut();
    this.router.navigate(['/new']);  
  }

  private currentUser(){
    this.user = this.loginService.currentUser();
  }
}

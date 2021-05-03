import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { LoginService } from '../../services/login.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user:User;

  constructor(private router:Router,public loginService:LoginService) { }

  ngOnInit() {
    this.currentUser();
  }

  ngDoCheck(){
    this.currentUser();
  }

  private currentUser(){
    this.user = this.loginService.currentUser();
  }
}

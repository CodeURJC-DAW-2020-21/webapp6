import { Component, OnInit } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';

import {LoginService} from '../../../services/login.service';
import {UserService} from '../../../services/user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../../../models/user.model';

@Component({
  selector: 'app-sign_in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})


export class SignInComponent implements OnInit {
  
  user:User;
 
  constructor( private router: Router,public LoginService : LoginService, activatedRoute:ActivatedRoute,public userService: UserService){ 
    let id=activatedRoute.snapshot.params['id'];
    this.user={roles:['User'], nickname:'',phonenumber: '' , mail: '', password:'',}; 
  }
  createUser(){
    this.userService.addUser(this.user).subscribe(data => {
      this.router.navigate(['/new/login']);
    },
    error => console.error('Error al crear el post '+error)
    );
  }

  ngOnInit() {
  }

}

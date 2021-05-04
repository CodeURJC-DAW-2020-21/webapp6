import { Component, OnInit } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';

import {loginService} from '../login.service';
import {UserService} from '../user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../../model/user.model';

@Component({
  selector: 'app-sign_in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})


export class SignInComponent implements OnInit {
 // private http: Http;
  //constructor() { }
                                                                            //, pn: Array<string>)
 /* signin(nickname: string, pass: string, email: string, phonenumber: string) {

    const data = {
      "username": nickname, "passwordHash": pass, "email": email,
      "phonenumber": phonenumber, "roles": ["ROLE_USER"]
    };
    const config = { headers: new HttpHeaders().set('Content-Type', 'application/json') };
    return this.http.post<any>('/api/auth/sign_in', data, config);
  } */

  user:User;
 
  constructor( private router: Router,public loginService : loginService, activatedRoute:ActivatedRoute,public userService: UserService){ 
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

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  password: string;

  constructor(public loginService:LoginService, private router: Router) { }

  logIn(event: any, user: string, pass: string) {
    event.preventDefault();
    this.loginService.logIn(user, pass);
    this.router.navigate(['/new']);    
  }

  ngOnInit() {
  }

}

import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})

/*
export class SignInComponent implements OnInit {

  constructor() { }

  signUp(un: string, pass: string, email: string, teamName: string, pn: Array<string>) {

    const data = {
      "userName": un, "passwordHash": pass, "email": email,
      "teamName": teamName, "roles": ["ROLE_USER"], "players": pn
    };
    const config = { headers: new HttpHeaders().set('Content-Type', 'application/json') };
    return this.http.post<any>('/api/tenniship/signup', data, config);
  }

  ngOnInit() {
  }

}*/

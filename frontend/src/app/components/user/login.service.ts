import { Injectable } from '@angular/core';
import { HttpClient,  HttpHeaders } from '@angular/common/http';
import { User } from '../model/user.model';

let BASE_URL = '/api/auth';

@Injectable({ providedIn: 'root' })
export class loginService{

    logged: boolean;
    user: User;

    constructor(private http: HttpClient) { 
        this.reqIsLogged();
    }

    
    reqIsLogged(){  
        this.http.get('/api/profiles/', { withCredentials: true }).subscribe(
            response => {
                this.user = response as User;
                this.logged = true;
            },
            error => {
                if (error.status != 404) {
                    console.error('Error when asking if logged: ' + JSON.stringify(error));
                }
            }
        );
    }  

    logIn(user: string, pass: string) {

        this.http.post(BASE_URL + "/login", { username: user, password: pass }, { withCredentials: true })
            .subscribe(
                (response) => this.reqIsLogged(),
                (error) => alert("Wrong credentials")
        
            );
                console.error();
                
    }


    logOut() {

        return this.http.post(BASE_URL + '/logout', { withCredentials: true })
            .subscribe((resp: any) => {
                console.log("LOGOUT: Successfully");
                this.logged = false;
                this.user = undefined;
            });

    }

    isLogged() {
        return this.logged;
    }

    isAdmin() {
        return this.user && this.user.roles.indexOf('ADMIN') !== -1;
    }

    currentUser() {
        return this.user;
    }
}
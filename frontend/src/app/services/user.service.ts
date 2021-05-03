import { Injectable } from '@angular/core';
import { User } from "../models/user.model";
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';


const BASE_URL = '/api/profile/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  addUser(user: User) {
    return this.http.post(BASE_URL, user).pipe(
      catchError(error => this.handleError(error))
    );
  }

  public getUser(id: number | string): Observable<User> {
    const url = BASE_URL + id;
    return this.http.get(url).pipe(
      catchError(error => this.handleError(error))
    ) as Observable<User>;
  }

  public getUsers(): Observable<User[]> {
    return this.http.get(BASE_URL, { withCredentials: true }).pipe(
      catchError(error => this.handleError(error))
    ) as Observable<User[]>;
  }

  private handleError(error: any) {
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }
}
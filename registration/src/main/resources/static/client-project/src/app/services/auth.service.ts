import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = new BehaviorSubject<boolean>(false);

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  constructor(private http: HttpClient, private router: Router) { }

  login(username, password) {
    const data = 'username=' + username + '&password=' + password + '&grant_type=password' +
    '&client_secret=client' + '&client_id=client';

    const reqHeader = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': 'Basic ' + btoa('client' + ':' + 'client')
    });
    this.loggedIn.next(true);
    return this.http.post('http://localhost:8080/oauth/token' , data, {headers: reqHeader});
  }

  logout() {
    this.router.navigate(['/login']);
    this.loggedIn.next(false);
    localStorage.removeItem('loggedUser');
  }

  public getToken(): string {
    return localStorage.getItem('userToken');
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';
import { LoggedUser } from '../models/logged-user';
import { PatientService } from './patient.service';
import { Patient } from '../models/patient.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = new BehaviorSubject<boolean>(false);
  private user: Patient;
  userRole: String;

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  constructor(private http: HttpClient, private router: Router, private patientService: PatientService) {
   }

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

  getRole() {
    const roles = localStorage.getItem('loggedUser');
   }

   getToken() {
    return localStorage.getItem('token');
  }
}

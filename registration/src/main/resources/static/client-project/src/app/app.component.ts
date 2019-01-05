import { Component, OnInit } from '@angular/core';
import { Person } from './models/person.model';
import { PatientService } from './services/patient.service';
import { Patient } from './models/patient.model';
import { Observable } from 'rxjs';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  isLoggedIn$: Observable<boolean>;
  title = 'client-project';
  currentUser: Patient;

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
       this.isLoggedIn$ = this.authService.isLoggedIn;
  }

  onLogout() {
    this.authService.logout();
}

   get username(): any {
    return localStorage.getItem('loggedUser.username');
   }

   get email(): any {
    return localStorage.getItem('loggedUser.email');
   }

   get loggedUser(): any {
    return localStorage.getItem('loggedUser');
   }
}

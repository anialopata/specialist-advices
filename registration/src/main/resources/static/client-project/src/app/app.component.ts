import { Component, OnInit } from '@angular/core';
import { Person } from './models/person.model';
import { PatientService } from './services/patient.service';
import { Patient } from './models/patient.model';
import { Observable } from 'rxjs';
import { AuthService } from './services/auth.service';
import { LoggedUser } from './models/logged-user';
import { MatDialog } from '@angular/material';
import { VisitService } from './services/visit.service';
import { Visit } from './models/visit.model';
import { UserVisitsDialogComponent } from './components/visit/user-visits-dialog/user-visits-dialog.component';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  isLoggedIn$: Observable<boolean>;
  title = 'client-project';
  loggedUser: LoggedUser;
  visits: Visit[];

  constructor(private authService: AuthService, private visitService: VisitService, private dialog: MatDialog) {
    this.loggedUser = JSON.parse(localStorage.getItem('loggedUser'));
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

   getPatientVisits() {
    this.visitService.getPatientVisits(this.loggedUser.id).subscribe(data => {
      this.visits = data;
      console.log(data);
    });
   }

  }



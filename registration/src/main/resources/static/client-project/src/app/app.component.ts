import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
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
import { EditProfilComponent } from './components/patient/edit-profil/edit-profil.component';
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
  user = new Patient();

  constructor(private authService: AuthService, private visitService: VisitService, private patientService: PatientService,
     private dialog: MatDialog, private cdr: ChangeDetectorRef) {
    this.loggedUser = JSON.parse(localStorage.getItem('loggedUser'));
  }

  ngOnInit() {
       this.isLoggedIn$ = this.authService.isLoggedIn;
  }


  onLogout() {
    this.authService.logout();
}

   getPatientVisits() {
    this.visitService.getPatientVisits(this.loggedUser.id).subscribe(data => {
      this.visits = data;
      console.log(data);
    });
   }

   getUser() {
     this.patientService.getPatientById(this.loggedUser.id).subscribe(data => {
      this.user = data;
      console.log(data);
     });
   }

    getUserData(currentUser: Patient) {
    console.log('nazwa  ' + currentUser.username);
    const name = currentUser.username;
    console.log('email  ' + currentUser.email);
    const email = currentUser.email;
    console.log('imie  ' + currentUser.firstName);
    const firstName = currentUser.firstName;
    console.log('naziwsko  ' + currentUser.lastName);
    const lastName = currentUser.lastName;

    this.patientService.getPatientById(this.loggedUser.id).subscribe(data => {
      this.user.username = currentUser.username;
      this.user.email = currentUser.email;
      this.user.firstName = currentUser.firstName;
      this.user.lastName = currentUser.lastName;

      console.log('nazwa: ' + this.user.username);
      console.log('email: ' + this.user.email );
      console.log('imie: ' + this.user.firstName);
      console.log('naziwsko: ' + this.user.lastName );
    }
    );
  }

  changeDataDialog(currentUser: Patient) {
    this.getUser();
    const dialogRef = this.dialog.open(EditProfilComponent, { data: currentUser });
    dialogRef.afterClosed().subscribe(result => {
      this.getUserData(currentUser);
    });
  }

  }



import { Component, OnInit } from '@angular/core';
import { Person } from './models/person.model';
import { PatientService } from './services/patient.service';
import { Patient } from './models/patient.model';
import { Observable } from 'rxjs';
import { AuthService } from './services/auth.service';
import { LoggedUser } from './models/logged-user';
import { MatDialog } from '@angular/material';
import { ChangePatientDataComponent } from './components/patient/dialogs/change-patient-data/change-patient-data.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  isLoggedIn$: Observable<boolean>;
  title = 'client-project';
  loggedUser: LoggedUser;

  constructor(private authService: AuthService, private dialog: MatDialog) {
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

  //  get loggedUser(): any {
  //   return localStorage.getItem('loggedUser.username');
  //  }

  // getLoggedUserData(user: LoggedUser) {
  //   this.loggedUser = JSON.parse(localStorage.getItem('loggedUser'));
  //   console.log('przekazana nazwa uzytownika ' + this.loggedUser.username);
  //   const name = user.username;
  //   console.log('przekazany mail uzytkownika ' + this.loggedUser.email);
  //   const email = user.email;

  //   this.categoryService.getCategoryById(currentCategory.id).subscribe(data => {
  //     this.category.name = currentCategory.name;
  //     this.category.description = currentCategory.description;

  //     console.log('name: ' + this.category.name);
  //     console.log('description: ' + this.category.description);
  //   }
  //   );
  }


  // changePatientDataDialog(user: LoggedUser) {
  //   this.loggedUser = JSON.parse(localStorage.getItem('loggedUser'));
  //   console.log('username: ' + this.loggedUser.username);
  //   console.log('email: ' + this.loggedUser.email);
  //   const dialogRef = this.dialog.open(ChangePatientDataComponent, { data: user });
  //   dialogRef.afterClosed().subscribe(result => {
  //     this.getCategoryData(currentCategory);
  //   });
  // }


import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { PatientService } from 'src/app/services/patient.service';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from 'src/app/services/auth.service';
import { BehaviorSubject } from 'rxjs';
import { LoggedUser } from 'src/app/models/logged-user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  isLoginError = false;
  private loggedIn = new BehaviorSubject<boolean>(false);

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    // reset login status
    this.authService.logout();
  }

  get f() { return this.loginForm.controls; }

  onSubmit(username, password) {
    this.authService.login(username, password).subscribe((data: any) => {
      const loggedUser = new LoggedUser();
      loggedUser.username = data.username;
      loggedUser.email = data.email;
      loggedUser.token = data.access_token;
      loggedUser.id = data.userId;
      loggedUser.phoneNumber = data.phoneNumber;
      loggedUser.firstName = data.firstName;
      loggedUser.lastName = data.lastName;
      loggedUser.pesel = data.pesel;
      loggedUser.password = data.password;

      localStorage.setItem('loggedUser', JSON.stringify(loggedUser));

      this.loggedIn.next(true);
      this.router.navigate(['/dashboard']);
    },
      (err: HttpErrorResponse) => {
        this.isLoginError = true;
      });
  }
}

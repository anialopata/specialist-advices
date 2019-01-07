import { Component, OnInit, Inject } from '@angular/core';
import { LoggedUser } from 'src/app/models/logged-user';
import { Patient } from 'src/app/models/patient.model';
import { PatientService } from 'src/app/services/patient.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { get } from 'http';

@Component({
  selector: 'app-edit-profil',
  templateUrl: './edit-profil.component.html',
  styleUrls: ['./edit-profil.component.scss']
})
export class EditProfilComponent implements OnInit {

  private user: Patient = new Patient();
  loggedUser: LoggedUser;
  registerForm: FormGroup;
  loading = false;
  submitted = false;
  emailPattern = '^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$';
  usernamePattern = '^[a-z0-9_-]{8,15}$';
  mobnumPattern = '^([0-9-+()\s]{9,16})?$';
  peselPattern = '^[0-9]{11}$';

  constructor( private formBuilder: FormBuilder,
        private router: Router, private patientService: PatientService, private dialogRef: MatDialogRef<EditProfilComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.user = data;
      this.loggedUser = JSON.parse(localStorage.getItem('loggedUser'));
     }

  ngOnInit() {
    this.getPatient();
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['',  [Validators.required, Validators.pattern(this.usernamePattern)]],
      email: ['',  [Validators.required, Validators.pattern(this.emailPattern)]],
      phoneNumber: ['',  [Validators.required, Validators.pattern(this.mobnumPattern)]],
      pesel: ['',  [Validators.required, Validators.pattern(this.peselPattern)]]
  });
  }

  getPatient() {
    this.patientService.getPatientById(this.loggedUser.id).subscribe(data => {
      this.user = data;
      console.log(data);
    });
   }

  get f() { return this.registerForm.controls; }

  onSaveClick(): void {
    this.submitted = true;

    if (this.registerForm.invalid) {
        return;
    }

  this.loading = true;
  this.patientService.updatePatient(this.user)
      .pipe(first())
      .subscribe(
          data => {
              this.router.navigate(['/login']);
          },
          error => {
              this.loading = false;
          });
}


}

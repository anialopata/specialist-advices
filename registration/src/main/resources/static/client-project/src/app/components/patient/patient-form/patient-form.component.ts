import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.scss']
})
export class PatientFormComponent implements OnInit {

  registerForm: FormGroup;
    loading = false;
    submitted = false;
    emailPattern = '^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$';
    usernamePattern = '^[a-z0-9_-]{8,15}$';
    mobnumPattern = '^([0-9-+()\s]{9,16})?$';
    peselPattern = '^[0-9]{11}$';

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private patientService: PatientService) { }

    ngOnInit() {
        this.registerForm = this.formBuilder.group({
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            username: ['',  [Validators.required, Validators.pattern(this.usernamePattern)]],
            password: ['', [Validators.required, Validators.minLength(6)]],
            email: ['',  [Validators.required, Validators.pattern(this.emailPattern)]],
            phoneNumber: ['',  [Validators.required, Validators.pattern(this.mobnumPattern)]],
            pesel: ['',  [Validators.required, Validators.pattern(this.peselPattern)]]
        });
    }

    get f() { return this.registerForm.controls; }

    onSubmit() {
        this.submitted = true;

        if (this.registerForm.invalid) {
            return;
        }

        this.loading = true;
        this.patientService.createNewPatient(this.registerForm.value)
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



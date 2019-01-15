import { Component, OnInit } from '@angular/core';
import { Specialist } from 'src/app/models/specialist.model';
import { SpecialistService } from 'src/app/services/specialist.service';
import { Category } from 'src/app/models/category.model';
import { CategoryService } from 'src/app/services/category.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-specialist-form',
  templateUrl: './specialist-form.component.html',
  styleUrls: ['./specialist-form.component.scss']
})
export class SpecialistFormComponent implements OnInit {

  specialist: Specialist = new Specialist();
  categories: Category[];
  selectedCategory: Category = new Category();

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
      private patientService: PatientService,
      private categoryService: CategoryService,
      private specialistService: SpecialistService) { }

  ngOnInit() {
     this.getCategories();
      this.registerForm = this.formBuilder.group({
          firstName: ['', Validators.required],
          lastName: ['', Validators.required],
          username: ['',  [Validators.required, Validators.pattern(this.usernamePattern)]],
          password: ['', [Validators.required, Validators.minLength(6)]],
          email: ['',  [Validators.required, Validators.pattern(this.emailPattern)]],
          phoneNumber: ['',  [Validators.required, Validators.pattern(this.mobnumPattern)]],
          pesel: ['',  [Validators.required, Validators.pattern(this.peselPattern)]],
          degree: ['', Validators.required],
          selectedCategory: ['', Validators.required]
      });
  }

  get f() { return this.registerForm.controls; }

  onSubmit() {
      this.submitted = true;

      if (this.registerForm.invalid) {
          return;
      }

      this.loading = true;
      this.specialistService.createNewSpecialist(this.registerForm.value)
          .pipe(first())
          .subscribe(
              data => {
                  this.router.navigate(['/login']);
              },
              error => {
                  this.loading = false;
              });
  }

getCategories() {
  this.categoryService.getCategories().subscribe(data => {
    this.categories = data;
    console.log(data);
  });
}

createSpecialist() {
  this.specialistService.createNewSpecialist(this.specialist).subscribe(data =>
    console.log(data)
    );
}

}

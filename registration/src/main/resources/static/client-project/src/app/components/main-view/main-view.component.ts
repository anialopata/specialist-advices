import { Component, OnInit } from '@angular/core';
import { SpecialistService } from 'src/app/services/specialist.service';
import { Person } from 'src/app/models/person.model';
import { Patient } from 'src/app/models/patient.model';
import { PatientService } from 'src/app/services/patient.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-main-view',
  templateUrl: './main-view.component.html',
  styleUrls: ['./main-view.component.scss']
})
export class MainViewComponent implements OnInit {

  currentUser: Patient;
  users: Patient[] = [];

  constructor(private specialistService: SpecialistService, private patientService: PatientService, private authService: AuthService) {
    this.currentUser = JSON.parse(localStorage.getItem('loggedUser'));
   }

  ngOnInit() {
    }
  }



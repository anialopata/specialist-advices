import { Component, OnInit, ViewChild } from '@angular/core';
import { Patient } from 'src/app/models/patient.model';
import { PatientService } from 'src/app/services/patient.service';
import { MatDialog, MatTableDataSource } from '@angular/material';
import { Observable } from 'rxjs';
import { VisitService } from 'src/app/services/visit.service';
import { UserVisitsDialogComponent } from '../visit/user-visits-dialog/user-visits-dialog.component';
import { PatientFormComponent } from './patient-form/patient-form.component';
import { DeletePatientComponent } from './dialogs/delete-patient/delete-patient.component';
import { Person } from 'src/app/models/person.model';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss']
})
export class PatientComponent implements OnInit {
  currentUser: Person;
  dataSource: Patient[];
  displayedColumns: string[] = ['firstName', 'lastName', 'pesel', 'email', 'gender', 'phoneNumber', 'actions', 'username', 'isActive'];

  patient = new Patient();

  constructor(private patientService: PatientService, private visitService: VisitService, private dialog: MatDialog) { 
    this.currentUser = JSON.parse(localStorage.getItem('loggedUser'));
  }

  ngOnInit() {
    this.patientService.getPatients().subscribe(data => {
      this.dataSource = data;
      console.log(data);
    });
  }

  getPatientVisits(currentPatient: Patient) {
    const dialogRef = this.dialog.open(UserVisitsDialogComponent, { data: currentPatient });
    dialogRef.afterClosed().subscribe(result => {
      this.getVisitData(currentPatient);
    });
  }

  getVisitData(currentPatient: Patient) {
    const visits = currentPatient.visits;

    this.patientService.getPatientById(currentPatient.id).subscribe(data => {
      this.patient.visits = currentPatient.visits;

      console.log('wizyty: ' + this.patient.visits);
    }
    );
  }

  deletePatientDialog(patientId: Number) {
    const dialogRef = this.dialog.open(DeletePatientComponent, { data: patientId });

  }

}





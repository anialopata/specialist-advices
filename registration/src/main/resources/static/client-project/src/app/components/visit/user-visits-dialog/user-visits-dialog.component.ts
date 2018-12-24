import { Component, OnInit } from '@angular/core';
import { VisitService } from 'src/app/services/visit.service';
import { MatDialogRef } from '@angular/material';
import { Visit } from 'src/app/models/visit.model';
import { Patient } from 'src/app/models/patient.model';

@Component({
  selector: 'app-user-visits-dialog',
  templateUrl: './user-visits-dialog.component.html',
  styleUrls: ['./user-visits-dialog.component.scss']
})
export class UserVisitsDialogComponent implements OnInit {

  dataSource: Visit[];
  displayedColumns: string[] = ['date', 'category', 'specialist', 'note', 'actions'];

  visits: Visit[] = [];

  constructor(private visitService: VisitService, private dialogRef: MatDialogRef<UserVisitsDialogComponent>) { }

  ngOnInit() {
    // this.getVisitsForPatient(selectedPatient);
  }

  getVisitsForPatient(selectedPatient: Patient) {
    console.log(selectedPatient);
    this.visitService.getPatientVisits(selectedPatient.id).subscribe(data => {
      selectedPatient.visits = data;
      console.log(data);
    });
  }
}

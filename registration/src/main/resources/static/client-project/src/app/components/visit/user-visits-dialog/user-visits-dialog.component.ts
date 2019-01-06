import { Component, OnInit } from '@angular/core';
import { VisitService } from 'src/app/services/visit.service';
import { MatDialogRef } from '@angular/material';
import { Visit } from 'src/app/models/visit.model';
import { Patient } from 'src/app/models/patient.model';
import { LoggedUser } from 'src/app/models/logged-user';

@Component({
  selector: 'app-user-visits-dialog',
  templateUrl: './user-visits-dialog.component.html',
  styleUrls: ['./user-visits-dialog.component.scss']
})
export class UserVisitsDialogComponent implements OnInit {

  dataSource: Visit[];
  displayedColumns: string[] = ['date', 'category', 'specialist', 'note', 'actions'];
  loggedUser: LoggedUser;
  visits: Visit[];

  constructor(private visitService: VisitService, private dialogRef: MatDialogRef<UserVisitsDialogComponent>) {
    this.loggedUser = JSON.parse(localStorage.getItem('loggedUser'));
   }

  ngOnInit() {
    this.getPatientVisits();
  }

  getPatientVisits() {
    this.visitService.getPatientVisits(this.loggedUser.id).subscribe(data => {
      this.dataSource = data;
      console.log(data);
    });
   }
}

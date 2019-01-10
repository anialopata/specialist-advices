import { Component, OnInit } from '@angular/core';
import { VisitService } from 'src/app/services/visit.service';
import { MatDialogRef, MatDialog } from '@angular/material';
import { Visit } from 'src/app/models/visit.model';
import { Patient } from 'src/app/models/patient.model';
import { LoggedUser } from 'src/app/models/logged-user';
import { DeleteVisitDialogComponent } from '../delete-visit-dialog/delete-visit-dialog.component';

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

  constructor(private visitService: VisitService, private dialogRef: MatDialogRef<UserVisitsDialogComponent>, private dialog: MatDialog) {
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

   deleteVisitDialog(visitId: Number) {
    const dialogRef = this.dialog.open(DeleteVisitDialogComponent, { data: visitId });
    dialogRef.afterClosed().subscribe(deleted => {
      if (deleted) {
          const index = this.dataSource.findIndex(visit => visit.id === visitId);
          const copyDataSource = this.dataSource.slice();
          copyDataSource.splice(index, 1);
          this.dataSource = copyDataSource;

      }

    });

  }
}

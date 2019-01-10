import { Component, OnInit } from '@angular/core';
import { VisitService } from 'src/app/services/visit.service';
import { Visit } from 'src/app/models/visit.model';
import { DeleteVisitDialogComponent } from './delete-visit-dialog/delete-visit-dialog.component';
import { MatDialog } from '@angular/material';
import { LoggedUser } from 'src/app/models/logged-user';
import { Person } from 'src/app/models/person.model';

@Component({
  selector: 'app-visit',
  templateUrl: './visit.component.html',
  styleUrls: ['./visit.component.scss']
})
export class VisitComponent implements OnInit {

  dataSource: Visit[];
  displayedColumns: string[] = ['date', 'category', 'specialist', 'note', 'patient'];
  loggedUser: Person;

  visit = new Visit();

  constructor(private visitService: VisitService, private dialog: MatDialog) {
    this.loggedUser = JSON.parse(localStorage.getItem('loggedUser'));
  }

  ngOnInit() {
    this.visitService.getVisits().subscribe(data => {
      this.dataSource = data;
      console.log(data);
    });
  }

  deleteVisit(id: Number) {
    this.visitService.deleteVisit(id);
  }

  getVisitData(currentVisit: Visit) {
    console.log('data: ' + currentVisit.date);
    const date = currentVisit.date;
    console.log('kategoria ' + currentVisit.category);
    const category = currentVisit.category;
    console.log('specjalista ' + currentVisit.specialist);
    const specialist = currentVisit.specialist;
    console.log('uwagi ' + currentVisit.note);
    const note = currentVisit.note;
    console.log('pacjent ' + currentVisit.patient);
    const patient = currentVisit.patient;

    this.visitService.getVisitById(currentVisit.id).subscribe(data => {
      this.visit.date = currentVisit.date;
      this.visit.category = currentVisit.category;
      this.visit.specialist = currentVisit.specialist;
      this.visit.note = currentVisit.note;
      this.visit.patient = currentVisit.patient;
    }
    );
  }

  deleteVisitDialog(visitId: Number) {
    const dialogRef = this.dialog.open(DeleteVisitDialogComponent, { data: visitId });
    dialogRef.afterClosed().subscribe(deleted => {
      if (deleted) {
          const index = this.dataSource.findIndex(visit => this.visit.id === visitId);
          const copyDataSource = this.dataSource.slice();
          copyDataSource.splice(index, 1);
          this.dataSource = copyDataSource;

      }

    });

  }

}

import { Component, OnInit } from '@angular/core';
import { SpecialistService } from 'src/app/services/specialist.service';
import { Specialist } from 'src/app/models/specialist.model';
import { Observable } from 'rxjs';
import { MatDialog } from '@angular/material';
import { Category } from 'src/app/models/category.model';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { SpecialistFormComponent } from './specialist-form/specialist-form.component';
import { DeleteSpecialistComponent } from './dialogs/delete-specialist/delete-specialist.component';
import { ChangeSpecialistDataComponent } from './dialogs/change-specialist-data/change-specialist-data.component';

@Component({
  selector: 'app-specialist',
  templateUrl: './specialist.component.html',
  styleUrls: ['./specialist.component.scss']
})
export class SpecialistComponent implements OnInit {

  dataSource: Specialist[];
  displayedColumns = ['firstName'];

  specialist = new Specialist();

  constructor(private specialistService: SpecialistService, private dialog: MatDialog) { }

  ngOnInit() {
    this.specialistService.getSpecialists().subscribe(data => {
      this.dataSource = data;
      console.log(data);
    });
  }

  deleteSpecialist(id: Number) {
    this.specialistService.deleteSpecialist(id);
  }

  getSpecialistData(currentSpecialist: Specialist) {
    console.log('przekazany stopień specjalisty ' + currentSpecialist.degree);
    const degree = currentSpecialist.degree;
    console.log('przekazane imię specjalisty ' + currentSpecialist.firstName);
    const firstName = currentSpecialist.firstName;
    console.log('przekazane nazwisko specjalisty ' + currentSpecialist.lastName);
    const lastName = currentSpecialist.lastName;
    console.log('przekazany opis specjalisty ' + currentSpecialist.description);
    const description = currentSpecialist.description;

    this.specialistService.getSpecialistById(currentSpecialist.id).subscribe(data => {
      this.specialist.firstName = currentSpecialist.firstName;
      this.specialist.lastName = currentSpecialist.firstName;
      this.specialist.degree = currentSpecialist.degree;
      this.specialist.description = currentSpecialist.description;

      console.log('firstName: ' + this.specialist.firstName);
      console.log('lastName: ' + this.specialist.lastName);
      console.log('degree: ' + this.specialist.degree);
      console.log('description: ' + this.specialist.description);
    }
    );
  }


  changeSpecialistData(currentSpecialist: Specialist) {
    console.log('firstName: ' + this.specialist.firstName);
    console.log('lastName: ' + this.specialist.lastName);
    console.log('degree: ' + this.specialist.degree);
    console.log('description: ' + this.specialist.description);
    const dialogRef = this.dialog.open(ChangeSpecialistDataComponent, { data: currentSpecialist });
    dialogRef.afterClosed().subscribe(result => {
      this.getSpecialistData(currentSpecialist);
    });
  }

  deleteSpecialistDialog(specialistId: Number) {
    const dialogRef = this.dialog.open(DeleteSpecialistComponent, { data: specialistId });
    dialogRef.afterClosed().subscribe(deleted => {
      if (deleted) {
          const index = this.dataSource.findIndex(specialist => specialist.id === specialistId);
          const copyDataSource = this.dataSource.slice();
          copyDataSource.splice(index, 1);
          this.dataSource = copyDataSource;

      }

    });

  }

}

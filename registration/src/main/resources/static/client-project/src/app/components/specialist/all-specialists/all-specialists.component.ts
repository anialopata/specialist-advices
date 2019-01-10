import { Component, OnInit } from '@angular/core';
import { Specialist } from 'src/app/models/specialist.model';
import { SpecialistService } from 'src/app/services/specialist.service';
import { MatDialog } from '@angular/material';
import { DeleteSpecialistComponent } from '../dialogs/delete-specialist/delete-specialist.component';

@Component({
  selector: 'app-all-specialists',
  templateUrl: './all-specialists.component.html',
  styleUrls: ['./all-specialists.component.scss']
})
export class AllSpecialistsComponent implements OnInit {

  dataSource: Specialist[];
  displayedColumns: string[] = ['firstName', 'lastName', 'degree', 'category', 'pesel', 'email', 'gender', 'phoneNumber',
                        'actions', 'username', 'isActive'];

  specialist = new Specialist();
  constructor(private specialistService: SpecialistService, private dialog: MatDialog) { }

  ngOnInit() {
    this.specialistService.getAllSpecialists().subscribe(data => {
      this.dataSource = data;
      console.log(data);
    });
  }

  deleteSpecialistDialog(specialistId: Number) {
    const dialogRef = this.dialog.open(DeleteSpecialistComponent, { data: specialistId });

  }

}

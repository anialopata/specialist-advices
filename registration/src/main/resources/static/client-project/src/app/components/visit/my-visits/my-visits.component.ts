import { Component, OnInit, Inject } from '@angular/core';
import { Patient } from 'src/app/models/patient.model';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Visit } from 'src/app/models/visit.model';
import { VisitService } from 'src/app/services/visit.service';

@Component({
  selector: 'app-my-visits',
  templateUrl: './my-visits.component.html',
  styleUrls: ['./my-visits.component.scss']
})
export class MyVisitsComponent implements OnInit {
  

  dataSource: Visit[];
  displayedColumns: string[] = ['date', 'category', 'specialist', 'note'];
  private selectedPatient: Patient = new Patient();

  constructor(private visitService: VisitService, private dialogRef: MatDialogRef<MyVisitsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.selectedPatient = data;
  }

  ngOnInit() {
    this.getPatientVisits();
  }

  getPatientVisits() {
    this.visitService.getPatientVisits(this.selectedPatient.id).subscribe(data => {
      this.dataSource = data;
      console.log(data);
    });
   }

  onCancelClick(): void {
    this.dialogRef.close();
  }

}

import { Component, OnInit, Inject } from '@angular/core';
import { LoggedUser } from 'src/app/models/logged-user';
import { PatientService } from 'src/app/services/patient.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-change-patient-data',
  templateUrl: './change-patient-data.component.html',
  styleUrls: ['./change-patient-data.component.scss']
})
export class ChangePatientDataComponent implements OnInit {

  private loggedUser: LoggedUser = new LoggedUser();

  constructor(private patientService: PatientService, private dialogRef: MatDialogRef<ChangePatientDataComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.loggedUser = data;
     }

  ngOnInit() {
  }

  onSaveClick(): void {
    this.patientService.updatePatient(this.loggedUser).subscribe(data => {
        this.dialogRef.close();
      });
  }

  onCancelClick(): void {
    this.dialogRef.close();

}

}

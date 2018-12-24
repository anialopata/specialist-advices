import { Component, OnInit, Inject } from '@angular/core';
import { PatientService } from 'src/app/services/patient.service';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-delete-patient',
  templateUrl: './delete-patient.component.html',
  styleUrls: ['./delete-patient.component.scss']
})
export class DeletePatientComponent implements OnInit {

  patientId: number;

  constructor(private patientService: PatientService, private dialogRef: MatDialogRef<DeletePatientComponent>,
    private dialog: MatDialog, @Inject(MAT_DIALOG_DATA) public data: any) {
      this.patientId = data;
    }

  ngOnInit() {
  }

  deletePatient(): void {
    this.patientService.deletePatient(this.patientId).subscribe(data => this.dialogRef.close(true));
    }

    onCancelClick(): void {
      this.dialogRef.close(false);
    }


}

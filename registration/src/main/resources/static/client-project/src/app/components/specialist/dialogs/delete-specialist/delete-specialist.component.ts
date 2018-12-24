import { Component, OnInit, Inject } from '@angular/core';
import { Specialist } from 'src/app/models/specialist.model';
import { SpecialistService } from 'src/app/services/specialist.service';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-delete-specialist',
  templateUrl: './delete-specialist.component.html',
  styleUrls: ['./delete-specialist.component.scss']
})
export class DeleteSpecialistComponent implements OnInit {

  specialistId: number;

  constructor(private specialistService: SpecialistService, private dialogRef: MatDialogRef<DeleteSpecialistComponent>,
    private dialog: MatDialog, @Inject(MAT_DIALOG_DATA) public data: any) {
      this.specialistId = data;
    }

  ngOnInit() {
  }

  deleteSpecialist(): void {
    this.specialistService.deleteSpecialist(this.specialistId).subscribe(data => this.dialogRef.close(true));
    }

    onCancelClick(): void {
      this.dialogRef.close(false);
    }

  }

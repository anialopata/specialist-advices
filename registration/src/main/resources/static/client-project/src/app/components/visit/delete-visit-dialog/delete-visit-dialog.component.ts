import { Component, OnInit, Inject } from '@angular/core';
import { VisitService } from 'src/app/services/visit.service';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-delete-visit-dialog',
  templateUrl: './delete-visit-dialog.component.html',
  styleUrls: ['./delete-visit-dialog.component.scss']
})
export class DeleteVisitDialogComponent implements OnInit {

  visitId: number;

  constructor(private visitService: VisitService, private dialogRef: MatDialogRef<DeleteVisitDialogComponent>,
    private dialog: MatDialog, @Inject(MAT_DIALOG_DATA) public data: any) {
      this.visitId = data;
    }

  ngOnInit() {
  }

  deleteVisit(): void {
    this.visitService.deleteVisit(this.visitId).subscribe(data => this.dialogRef.close(true));
    }

    onCancelClick(): void {
      this.dialogRef.close(false);
    }

  }

import { Component, OnInit, Inject } from '@angular/core';
import { Visit } from 'src/app/models/visit.model';
import { VisitService } from 'src/app/services/visit.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-change-visit-data-dialog',
  templateUrl: './change-visit-data-dialog.component.html',
  styleUrls: ['./change-visit-data-dialog.component.scss']
})
export class ChangeVisitDataDialogComponent implements OnInit {

  private selectedVisit: Visit = new Visit();

  constructor(private visitService: VisitService, private dialogRef: MatDialogRef<ChangeVisitDataDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.selectedVisit = data;
     }

  ngOnInit() {
  }

  onSaveClick(): void {
    this.visitService.updateVisit(this.selectedVisit).subscribe(data => {
        this.dialogRef.close();
      });
  }

  onCancelClick(): void {
    this.dialogRef.close();

}
}

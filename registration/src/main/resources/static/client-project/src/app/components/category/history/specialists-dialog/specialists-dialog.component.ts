import { Component, OnInit, Inject } from '@angular/core';
import { Category } from 'src/app/models/category.model';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-specialists-dialog',
  templateUrl: './specialists-dialog.component.html',
  styleUrls: ['./specialists-dialog.component.scss']
})
export class SpecialistsDialogComponent implements OnInit {

  private selectedCategory: Category = new Category();

  constructor(private dialogRef: MatDialogRef<SpecialistsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.selectedCategory = data;
  }

  ngOnInit() {
  }
  
  onCancelClick(): void {
    this.dialogRef.close();
  }
}

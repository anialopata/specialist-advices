import { Component, OnInit, Inject } from '@angular/core';
import { CategoryService } from 'src/app/services/category.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Category } from 'src/app/models/category.model';

@Component({
  selector: 'app-change-data-dialog',
  templateUrl: './change-data-dialog.component.html',
  styleUrls: ['./change-data-dialog.component.scss']
})
export class ChangeDataDialogComponent implements OnInit {

  private selectedCategory: Category = new Category();

  constructor(private categoryService: CategoryService, private dialogRef: MatDialogRef<ChangeDataDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.selectedCategory = data;
     }

  ngOnInit() {
  }

  onSaveClick(): void {
    this.categoryService.updateCategory(this.selectedCategory).subscribe(data => {
        this.dialogRef.close();
      });
  }

  onCancelClick(): void {
    this.dialogRef.close();

}


}

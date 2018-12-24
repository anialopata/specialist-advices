import { Component, OnInit, Inject } from '@angular/core';
import { CategoryService } from 'src/app/services/category.service';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material';
import { Category } from 'src/app/models/category.model';

@Component({
  selector: 'app-delete-category',
  templateUrl: './delete-category.component.html',
  styleUrls: ['./delete-category.component.scss']
})
export class DeleteCategoryComponent implements OnInit {
  categoryId: number;

  constructor(private categoryService: CategoryService, private dialogRef: MatDialogRef<DeleteCategoryComponent>,
    private dialog: MatDialog, @Inject(MAT_DIALOG_DATA) public data: any) {
      this.categoryId = data;
    }

  ngOnInit() {
  }

  deleteCategory(): void {
    this.categoryService.deleteCategory(this.categoryId).subscribe(data => this.dialogRef.close());
    }

    onCancelClick(): void {
      this.dialogRef.close(false);
    }

  }

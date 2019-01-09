import { Component, OnInit } from '@angular/core';
import { CategoryService } from 'src/app/services/category.service';
import { Category } from 'src/app/models/category.model';
import { MatDialog } from '@angular/material';
import { SpecialistService } from 'src/app/services/specialist.service';
import { ChangeDataDialogComponent } from './dialogs/change-data-dialog/change-data-dialog.component';
import { DeleteCategoryComponent } from './dialogs/delete-category/delete-category.component';
import { Person } from 'src/app/models/person.model';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {

  dataSource: Category[];
  displayedColumns = ['name'];
  currentUser: Person;

  category = new Category();

  constructor(private categoryService: CategoryService, private dialog: MatDialog, private specialistService: SpecialistService) { 
    this.currentUser = JSON.parse(localStorage.getItem('loggedUser'));
  }

  ngOnInit() {
    this.categoryService.getCategories().subscribe(data => {
      this.dataSource = data;
      console.log(data);
    });
  }

  deleteCategory(id: Number) {
    this.categoryService.deleteCategory(id);
  }

  getCategoryData(currentCategory: Category) {
    console.log('przekazana nazwa kategorii ' + currentCategory.name);
    const name = currentCategory.name;
    console.log('przekazany opis kategorii ' + currentCategory.description);
    const description = currentCategory.description;

    this.categoryService.getCategoryById(currentCategory.id).subscribe(data => {
      this.category.name = currentCategory.name;
      this.category.description = currentCategory.description;

      console.log('name: ' + this.category.name);
      console.log('description: ' + this.category.description);
    }
    );
  }

  changeDataDialog(currentCategory: Category) {
    console.log('name: ' + this.category.name);
    console.log('current name: ' + currentCategory.name);
    console.log('description: ' + this.category.description);
    console.log('current description: ' + currentCategory.description);
    const dialogRef = this.dialog.open(ChangeDataDialogComponent, { data: currentCategory });
    dialogRef.afterClosed().subscribe(result => {
      this.getCategoryData(currentCategory);
    });
  }

  deleteCategoryDialog(categoryId: Number) {
    const dialogRef = this.dialog.open(DeleteCategoryComponent, { data: categoryId });
    dialogRef.afterClosed().subscribe(deleted => {
      if (deleted) {
        const index = this.dataSource.findIndex(category => category.id === categoryId);
        const copyDataSource = this.dataSource.slice();
        copyDataSource.splice(index, 1);
        this.dataSource = copyDataSource;
      }
    });

  }

}

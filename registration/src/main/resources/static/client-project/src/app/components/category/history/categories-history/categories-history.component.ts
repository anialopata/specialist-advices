import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/category.model';
import { Person } from 'src/app/models/person.model';
import { CategoryService } from 'src/app/services/category.service';
import { MatDialog } from '@angular/material';
import { SpecialistsDialogComponent } from '../specialists-dialog/specialists-dialog.component';

@Component({
  selector: 'app-categories-history',
  templateUrl: './categories-history.component.html',
  styleUrls: ['./categories-history.component.scss']
})
export class CategoriesHistoryComponent implements OnInit {

  dataSource: Category[];
  displayedColumns: string[] = ['name', 'isActive', 'specialists']
  loggedUser: Person;

  category = new Category();

  constructor(private categoryService: CategoryService, private dialog: MatDialog) {
    this.loggedUser = JSON.parse(localStorage.getItem('loggedUser'));
  }

  ngOnInit() {
    this.categoryService.getCategories().subscribe(data => {
      this.dataSource = data;
      console.log(data);
    });
  }

  categoryDetails(currentCategory: Category) {
    console.log("specjaliści: " + currentCategory.specialists);
    const specialists = currentCategory.specialists;

    const dialogRef = this.dialog.open(SpecialistsDialogComponent, { data: currentCategory });

  }
}

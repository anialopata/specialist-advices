import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/category.model';
import { Specialist } from 'src/app/models/specialist.model';
import { CategoryService } from 'src/app/services/category.service';
import { SpecialistService } from 'src/app/services/specialist.service';

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.scss']
})
export class CategoryFormComponent implements OnInit {

  category: Category = new Category();
  specialists: Specialist[];
  constructor(private specialistService: SpecialistService, private categoryService: CategoryService) { }

  ngOnInit() {
    this.specialistService.getSpecialists().subscribe(data => {
      this.specialists = data;
      console.log(data);
    });
  }
    createCategory() {
      this.categoryService.createNewCategory(this.category).subscribe(data =>
        console.log(data)
        );
    }
}

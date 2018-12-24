import { Component, OnInit } from '@angular/core';
import { SpecialistService } from 'src/app/services/specialist.service';
import { VisitService } from 'src/app/services/visit.service';
import { Visit } from 'src/app/models/visit.model';
import { Category } from 'src/app/models/category.model';
import { Specialist } from 'src/app/models/specialist.model';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-visit-form',
  templateUrl: './visit-form.component.html',
  styleUrls: ['./visit-form.component.scss']
})
export class VisitFormComponent implements OnInit {

  visit: Visit = new Visit();
  categories: Category[];
  specialists: Specialist[];

  constructor(private visitService: VisitService, private categoryService: CategoryService,
    private specialistService: SpecialistService) { }

  ngOnInit() {
    this.categoryService.getCategories().subscribe(data => {
      this.categories = data;
      console.log(data);
    });

    this.specialistService.getSpecialists().subscribe(data => {
      this.specialists = data;
      console.log(data);
    });
  }

  createVisit() {
    this.visitService.createNewVisit(this.visit).subscribe(data =>
      console.log(data)
      );
  }

}

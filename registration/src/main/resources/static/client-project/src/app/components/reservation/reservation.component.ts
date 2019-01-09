import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { SpecialistService } from 'src/app/services/specialist.service';
import { CategoryService } from 'src/app/services/category.service';
import { Specialist } from 'src/app/models/specialist.model';
import { Category } from 'src/app/models/category.model';
import { VisitService } from 'src/app/services/visit.service';
import { Week } from 'src/app/models/week';
import { SimpleVisit } from 'src/app/models/simple-visit';
import { Visit } from 'src/app/models/visit.model';
import { MatDialog } from '@angular/material';
import { ShowVisitDetailsDialogComponent } from './show-visit-details-dialog/show-visit-details-dialog.component';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.scss']
})
export class ReservationComponent implements OnInit {
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  specialists: Specialist[] = [];
  categories: Category[] = [];
  category: Category = new Category();
  week: Week;
  // simpleVisit: SimpleVisit[];
  // selectedCategory: Category;
  // selectedSpecialist: Specialist;
  selectedVisit = new Visit();
  weekKeys: string[];
  visit: Visit[];
  addWeek: number;

  constructor(private _formBuilder: FormBuilder, private categoryService: CategoryService, private specialistService: SpecialistService,
    private visitService: VisitService, private dialog: MatDialog) {}

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
     this.getCategories();
  }

createReservation(date: Date) {
  console.log(date);
}

getWeek() {
  this.visitService.getSpecialistWeek(this.selectedVisit.specialist.id, new Date(), this.addWeek).subscribe(data => {
    const additionalWeek = 7;
    const start = new Date();
    // this.getFreeHours(data, new Date() + (additionalWeek * addWeek));
    this.weekKeys = Object.keys(this.week);
    console.log(data);
  });
}

// getWeekPlusOne() {
//   this.visitService.getSpecialistWeek(this.selectedVisit.specialist.id, new Date(), 1).subscribe(data => {
//     this.getFreeHours(data, new Date());
//     this.weekKeys = Object.keys(data);
//     console.log(data);
//   });
// }

// parsowNIE DATY z serwera new Date(date_string)

  private getCategories() {
    this.categoryService.getCategories().subscribe(data => {
      this.categories = data;
    });
  }

  getSpecialistsForCategory(selectedCategory: Category) {
    console.log(selectedCategory);
    this.specialistService.getSpecialistsForCategory(selectedCategory.id).subscribe(data => {
      this.specialists = data;
  });
  }

  private getFreeHours(hoursReserved: Week, startDate: Date) {
    this.week = new Week();
    Object.keys(hoursReserved).forEach( (key, i) => {
      const date: Date = new Date(startDate.getTime());
      date.setDate(startDate.getDate() + i);
      for (let a = 8; a <= 16; a++) {
        const dateWithHour = new Date(date.getTime());
        dateWithHour.setHours(a, 0, 0, 0);
        if (!this.isDateReserved(dateWithHour, hoursReserved, key)) {
          const visit = new Visit();
          visit.id = this.selectedVisit.id;
          visit.date = dateWithHour;
          visit.category = this.selectedVisit.category;
          visit.specialist = this.selectedVisit.specialist;
          this.week[key].push(visit);
        }
      }
  });
  console.log(this.week);
}

private isDateReserved(date: Date, hoursReserved: Week, key: string): boolean {
  return hoursReserved[key].some(visit => {
    return new Date(visit.date).getHours() === date.getHours(); // po uporzadkowaniu na backendzie przywrocic getTime
  });
}

getVisitData(currentVisit: Visit) {
  console.log('data: ' + currentVisit.date);
  const date = currentVisit.date;
  console.log('kategoria ' + currentVisit.category);
  const category = currentVisit.category;
  console.log('specjalista ' + currentVisit.specialist);
  const specialist = currentVisit.specialist;
  console.log('uwagi ' + currentVisit.note);
  const note = currentVisit.note;

  const dialogRef = this.dialog.open(ShowVisitDetailsDialogComponent, { data: currentVisit });
  dialogRef.afterClosed().subscribe(result => {
    this.getVisitData(currentVisit);
  });

}

}

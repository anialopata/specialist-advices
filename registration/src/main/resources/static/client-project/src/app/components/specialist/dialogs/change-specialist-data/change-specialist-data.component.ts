import { Component, OnInit, Inject } from '@angular/core';
import { SpecialistService } from 'src/app/services/specialist.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Specialist } from 'src/app/models/specialist.model';
import { CategoryService } from 'src/app/services/category.service';
import { Category } from 'src/app/models/category.model';

@Component({
  selector: 'app-change-specialist-data',
  templateUrl: './change-specialist-data.component.html',
  styleUrls: ['./change-specialist-data.component.scss']
})
export class ChangeSpecialistDataComponent implements OnInit {

  private selectedSpecialist: Specialist = new Specialist();
  categories: Category[];

   constructor(private specialistService: SpecialistService, private categoryService: CategoryService,
    private dialogRef: MatDialogRef<ChangeSpecialistDataComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.selectedSpecialist = data;
     }

     ngOnInit() {
      this.categoryService.getCategories().subscribe(data => {
        this.categories = data;
        console.log(data);
      });
    }

    onSaveClick(): void {
      this.specialistService.updateSpecialist(this.selectedSpecialist).subscribe(data => {
          this.dialogRef.close();
        });
    }

    onCancelClick(): void {
      this.dialogRef.close();
}
}

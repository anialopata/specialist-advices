import { Component, OnInit, Inject, LOCALE_ID } from '@angular/core';
import { Visit } from 'src/app/models/visit.model';
import { VisitService } from 'src/app/services/visit.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { DatePipe } from '@angular/common';
import { VisitDto } from 'src/app/models/visit-dto';
import { SpecialistService } from 'src/app/services/specialist.service';
import { LoggedUser } from 'src/app/models/logged-user';


@Component({
  selector: 'app-show-visit-details-dialog',
  templateUrl: './show-visit-details-dialog.component.html',
  styleUrls: ['./show-visit-details-dialog.component.scss']
})
export class ShowVisitDetailsDialogComponent implements OnInit {

  private selectedVisit: VisitDto = new VisitDto();
  loggedUser: LoggedUser;

  constructor(private specialistService: SpecialistService, private dialogRef: MatDialogRef<ShowVisitDetailsDialogComponent>,
    private datePipe: DatePipe = new DatePipe('pl'),
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.selectedVisit = data;
    this.loggedUser = JSON.parse(localStorage.getItem('loggedUser'));
  }

  ngOnInit() {
  }

  onSaveClick(): void {
    this.transformDate(this.selectedVisit.date);
    this.specialistService.addVisitToSpecialist(this.selectedVisit.specialist.id, this.selectedVisit, this.loggedUser.id)
      .subscribe(data => {
        this.dialogRef.close();
      });
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }

  transformDate(date) {
    this.selectedVisit.date = this.datePipe.transform(date, 'yyyy-MM-dd HH:mm');
  }

}



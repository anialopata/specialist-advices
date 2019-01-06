import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainViewComponent } from './components/main-view/main-view.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CategoryComponent } from './components/category/category.component';
import {
  MatButtonModule, MatCardModule, MatDialogModule, MatInputModule, MatTableModule,
  MatToolbarModule,  MatListModule, MatExpansionModule, MatDatepickerModule, MatNativeDateModule, MatSidenavModule, MatIconModule,
  MatFormFieldModule, MatOptionModule, MatSelectModule, MatRadioModule, MAT_DIALOG_DATA, MatDialogRef,
  MatPaginatorModule, MatStepperModule  } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { SpecialistComponent } from './components/specialist/specialist.component';
import { SpecialistFormComponent } from './components/specialist/specialist-form/specialist-form.component';
import { CategoryFormComponent } from './components/category/category-form/category-form.component';
import { PatientComponent } from './components/patient/patient.component';
import { PatientFormComponent } from './components/patient/patient-form/patient-form.component';
import { VisitComponent } from './components/visit/visit.component';
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';
import { ChangeDataDialogComponent } from './components/category/dialogs/change-data-dialog/change-data-dialog.component';
import { DeleteCategoryComponent } from './components/category/dialogs/delete-category/delete-category.component';
import { ChangeSpecialistDataComponent } from './components/specialist/dialogs/change-specialist-data/change-specialist-data.component';
import { DeleteSpecialistComponent } from './components/specialist/dialogs/delete-specialist/delete-specialist.component';
import { ReservationComponent } from './components/reservation/reservation.component';
import { ShowVisitDetailsDialogComponent } from './components/reservation/show-visit-details-dialog/show-visit-details-dialog.component';
import { ChangeVisitDataDialogComponent } from './components/visit/change-visit-data-dialog/change-visit-data-dialog.component';
import { DeleteVisitDialogComponent } from './components/visit/delete-visit-dialog/delete-visit-dialog.component';
import { DatePipe } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { AuthInterceptor } from './components/auth.interceptor';
import { UserVisitsDialogComponent } from './components/visit/user-visits-dialog/user-visits-dialog.component';
import { AllSpecialistsComponent } from './components/specialist/all-specialists/all-specialists.component';
import { DeletePatientComponent } from './components/patient/dialogs/delete-patient/delete-patient.component';
import { registerLocaleData } from '@angular/common';
import localePl from '@angular/common/locales/pl';
import { MyVisitsComponent } from './components/visit/my-visits/my-visits.component';

registerLocaleData(localePl);

@NgModule({
  declarations: [
    AppComponent,
    MainViewComponent,
    CategoryComponent,
    SpecialistComponent,
    SpecialistFormComponent,
    CategoryFormComponent,
    PatientComponent,
    PatientFormComponent,
    VisitComponent,
    WelcomePageComponent,
    ChangeDataDialogComponent,
    DeleteCategoryComponent,
    ChangeSpecialistDataComponent,
    DeleteSpecialistComponent,
    ReservationComponent,
    ShowVisitDetailsDialogComponent,
    ChangeVisitDataDialogComponent,
    DeleteVisitDialogComponent,
    LoginComponent,
    UserVisitsDialogComponent,
    AllSpecialistsComponent,
    DeletePatientComponent,
    MyVisitsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    HttpClientModule,
    MatDialogModule,
    MatTableModule,
    MatExpansionModule,
    MatNativeDateModule,
    MatSidenavModule,
    FormsModule,
    MatCheckboxModule,
    MatIconModule,
    MatFormFieldModule, MatOptionModule, MatSelectModule,
    MatRadioModule,
    MatDatepickerModule,
    MatPaginatorModule,
    MatStepperModule,
    ReactiveFormsModule
  ],
  entryComponents: [ChangeSpecialistDataComponent],
  providers: [{ provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: MatDialogRef, useValue: {} },
    { provide: DatePipe },
    { provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }

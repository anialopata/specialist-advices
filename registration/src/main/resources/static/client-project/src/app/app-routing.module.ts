import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainViewComponent } from './components/main-view/main-view.component';
import { CategoryComponent } from './components/category/category.component';
import { SpecialistComponent } from './components/specialist/specialist.component';
import { SpecialistFormComponent } from './components/specialist/specialist-form/specialist-form.component';
import { CategoryFormComponent } from './components/category/category-form/category-form.component';
import { PatientFormComponent } from './components/patient/patient-form/patient-form.component';
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';
import { PatientComponent } from './components/patient/patient.component';
import { ChangeDataDialogComponent } from './components/category/dialogs/change-data-dialog/change-data-dialog.component';
import { DeleteCategoryComponent } from './components/category/dialogs/delete-category/delete-category.component';
import { DeleteSpecialistComponent } from './components/specialist/dialogs/delete-specialist/delete-specialist.component';
import { ChangeSpecialistDataComponent } from './components/specialist/dialogs/change-specialist-data/change-specialist-data.component';
import { VisitComponent } from './components/visit/visit.component';
import { ReservationComponent } from './components/reservation/reservation.component';
import { ShowVisitDetailsDialogComponent } from './components/reservation/show-visit-details-dialog/show-visit-details-dialog.component';
import { ChangeVisitDataDialogComponent } from './components/visit/change-visit-data-dialog/change-visit-data-dialog.component';
import { DeleteVisitDialogComponent } from './components/visit/delete-visit-dialog/delete-visit-dialog.component';
import { LoginComponent } from './components/login/login.component';
import { UserVisitsDialogComponent } from './components/visit/user-visits-dialog/user-visits-dialog.component';
import { AllSpecialistsComponent } from './components/specialist/all-specialists/all-specialists.component';
import { DeletePatientComponent } from './components/patient/dialogs/delete-patient/delete-patient.component';


const routes: Routes = [

  { path: 'home', component: WelcomePageComponent },
  { path: 'signup/patient', component: PatientFormComponent },
  { path: 'signup/specialist', component: SpecialistFormComponent },
  { path: 'login', component: LoginComponent },

  { path: 'dashboard', component: MainViewComponent},

  { path: 'reservation', component: ReservationComponent},
  { path: 'reservation', component: ShowVisitDetailsDialogComponent},

  { path: 'categories', component: CategoryComponent},
  { path: 'categories', component: ChangeDataDialogComponent},
  { path: 'categories', component: DeleteCategoryComponent},
  { path: 'category/form', component: CategoryFormComponent},

  { path: 'specialists', component: SpecialistComponent},
  { path: 'specialists', component: ChangeSpecialistDataComponent},
  { path: 'specialists', component: DeleteSpecialistComponent},

  { path: 'visits', component: VisitComponent},

  { path: 'specialists/all', component: AllSpecialistsComponent},
  { path: 'specialists/all', component: DeleteSpecialistComponent},
  { path: 'patients', component: PatientComponent},
  { path: 'patients', component: DeletePatientComponent}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

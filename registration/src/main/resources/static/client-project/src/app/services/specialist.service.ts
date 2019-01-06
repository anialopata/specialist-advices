import { Injectable } from '@angular/core';
import { Specialist} from '../models/specialist.model';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';
import { Category } from '../models/category.model';
import { Visit } from '../models/visit.model';
import { AstVisitor } from '@angular/animations/browser/src/dsl/animation_ast';
import { VisitDto } from '../models/visit-dto';
import { LoggedUser } from '../models/logged-user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class SpecialistService {

  constructor(private http: HttpClient) {}

  public getSpecialists(): Observable<Specialist[]> {
    return this.http.get<any>('http://localhost:8080/api/v1/specialists')
          .pipe(
            map(specialistList => {
              return specialistList.specialists;
            }));
  }

  public deleteSpecialist(id: Number) {
    return this.http.delete<Specialist>('http://localhost:8080/api/v1/specialists/' + id);
  }

  public createNewSpecialist(specialist: Specialist): Observable<any> {
    return this.http.post<Specialist>('http://localhost:8080/signup/specialist', specialist);
  }

  public updateSpecialist(specialist: Specialist) {
    return this.http.put<Specialist>('http://localhost:8080/api/v1/specialists/' + specialist.id, specialist);
  }

  public getSpecialistById(id: Number): Observable<Specialist> {
    return this.http.get<Specialist>('http://localhost:8080/api/v1/specialists/' + id);
  }

  public getSpecialistsForCategory(categoryId: Number): Observable<Specialist[]> {
    return this.http.get<any>(`http://localhost:8080/api/v1/categories/${categoryId}/specialists`);
  }

  public addVisitToSpecialist(specialistId: number, visit: VisitDto, patientId: number): Observable<any> {
    return this.http.post<VisitDto>(`//localhost:8080/api/v1/specialists/${specialistId}/visits/patient/${patientId}`, visit);
  }


}

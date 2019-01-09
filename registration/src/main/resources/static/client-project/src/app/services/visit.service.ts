import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';
import { Visit } from '../models/visit.model';
import { Week } from '../models/week';
import { DatePipe } from '@angular/common';
import { VisitDto } from '../models/visit-dto';

@Injectable({
  providedIn: 'root'
})
export class VisitService {

  constructor(private http: HttpClient) {}

  public getVisits(): Observable<Visit[]> {
    return this.http.get<any>('http://localhost:8080/api/v1/visits')
          .pipe(
            map(visitList => {
              return visitList.visits;
            }));
  }

  public deleteVisit(id: Number) {
    return this.http.delete<Visit>('http://localhost:8080/api/v1/visits/' + id);
  }

  public createNewVisit(visit: VisitDto): Observable<any> {
    return this.http.post<VisitDto>('http://localhost:8080/api/v1/visits/', visit);
  }

  public updateVisit(visit: Visit) {
    return this.http.put<Visit>('http://localhost:8080/api/v1/visits/' + visit.id, visit);
  }

  public getVisitById(id: Number): Observable<Visit> {
    return this.http.get<Visit>('http://localhost:8080/api/v1/visits/' + id);
  }

  public getSpecialistWeek(specialistId: number, date: Date, weekNo: number) {
    return this.http.get<Week>(`http://localhost:8080/api/v1/weeks/${specialistId}/${date.toISOString().slice(0, 10)}/${weekNo}`);
  }

  public getPatientVisits(patientId: number): Observable<Visit[]> {
    return this.http.get<Visit[]>(`http://localhost:8080/api/v1/patients/${patientId}/visits`);
  }


}

import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';
import { Patient } from '../models/patient.model';


@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) { }

  public getPatients(): Observable<Patient[]> {
    return this.http.get<any>('//localhost:8080/api/v1/patients')
      .pipe(
        map(patientList => {
          return patientList.patients;
        }));
  }

  public getPatientById(id: Number): Observable<Patient> {
    return this.http.get<Patient>('http://localhost:8080/api/v1/patients/' + id);
  }


  public deletePatient(id: Number) {
    return this.http.delete<Patient>('//localhost:8080/api/v1/patients/' + id);
  }

  public createNewPatient(patient: Patient): Observable<any> {
    return this.http.post('http://localhost:8080/signup/patient', patient);
  }


  public updatePatient(patient: Patient) {
    return this.http.put<Patient>('//localhost:8080/api/patients' + patient.id, patient);
  }

  patientAuthentication(username, password) {
    const data = 'username=' + username + '&password=' + password + '&grant_type=password' +
    '&client_secret=client' + '&client_id=client';

    const reqHeader = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': 'Basic ' + btoa('client' + ':' + 'client')
    });

    return this.http.post('http://localhost:8080/oauth/token' , data, {headers: reqHeader});
  }

    getToken() {
    return localStorage.getItem('token');
  }
}



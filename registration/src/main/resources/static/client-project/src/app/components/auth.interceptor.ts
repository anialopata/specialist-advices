import { HttpInterceptor, HttpRequest, HttpHandler, HttpUserEvent, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable, Injector } from '@angular/core';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { PatientService } from '../services/patient.service';
import { AuthService } from '../services/auth.service';
import { LoggedUser } from '../models/logged-user';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(public authService: AuthService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const loggedUser = localStorage.getItem('loggedUser');
        if (loggedUser && JSON.parse(loggedUser).token) {
            request = request.clone({
                headers: request.headers.set('Authorization', 'Bearer ' + JSON.parse(loggedUser).token)
            });
        }

        return next.handle(request);
    }
}
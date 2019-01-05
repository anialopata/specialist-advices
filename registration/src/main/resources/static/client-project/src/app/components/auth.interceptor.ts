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

// intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
//     const currentUser = JSON.parse(localStorage.getItem('userToken'));
//     if (currentUser) {
//         request = request.clone({
//             setHeaders: {
//                 Authorization: `Bearer ${currentUser}`
//             }
//         });
//     }

//     return next.handle(request);

    // constructor(private injector: Injector, private router: Router) { }

    // intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    //     if (req.headers.get('No-Auth') === 'True') {
    //         return next.handle(req.clone());
    //     }

    //     if (localStorage.getItem('userToken') != null) {
    //         const patientService = this.injector.get(PatientService);
    //         const clonedreq = req.clone({
    //             headers: req.headers.set('Authorization', `Bearer ${patientService.getToken()}`)
    //         });
    //         return next.handle(clonedreq)
    //         .pipe(tap(
    //             succ => { },
    //             err => {
    //                 if (err.status === 401) {
    //                     this.router.navigateByUrl('/login');
    //                 }
    //             }
    //         ));
    //     } else {
    //         this.router.navigateByUrl('/login');
    //     }
    // }


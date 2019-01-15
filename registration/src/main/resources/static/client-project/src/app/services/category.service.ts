import { Injectable } from '@angular/core';

import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';
import { Category } from '../models/category.model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) {}

  public getCategories(): Observable<Category[]> {
    return this.http.get<any>('http://localhost:8080/api/v1/categories')
          .pipe(
            map(categoriesList => {
              return categoriesList.categories;
            }));
  }

  public getActiveCategories(): Observable<Category[]> {
    return this.http.get<any>('http://localhost:8080/api/v1/categories/active')
          .pipe(
            map(categoriesList => {
              return categoriesList.categories;
            }));
  }

  public deleteCategory(id: Number) {
    return this.http.delete<Category>('http://localhost:8080/api/v1/categories/' + id);
  }

  public createNewCategory(category: Category): Observable<any> {
    return this.http.post<Category>('http://localhost:8080/api/v1/categories', category);
  }

  public updateCategory(category: Category) {
    return this.http.put<Category>('http://localhost:8080/api/v1/categories/' + category.id, category);
  }

  public getCategoryById(id: Number): Observable<Category> {
    return this.http.get<Category>('http://localhost:8080/api/v1/categories/' + id);
  }
}




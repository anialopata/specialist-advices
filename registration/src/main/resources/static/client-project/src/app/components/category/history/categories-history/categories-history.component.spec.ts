import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoriesHistoryComponent } from './categories-history.component';

describe('CategoriesHistoryComponent', () => {
  let component: CategoriesHistoryComponent;
  let fixture: ComponentFixture<CategoriesHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CategoriesHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoriesHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeVisitDataDialogComponent } from './change-visit-data-dialog.component';

describe('ChangeVisitDataDialogComponent', () => {
  let component: ChangeVisitDataDialogComponent;
  let fixture: ComponentFixture<ChangeVisitDataDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeVisitDataDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeVisitDataDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

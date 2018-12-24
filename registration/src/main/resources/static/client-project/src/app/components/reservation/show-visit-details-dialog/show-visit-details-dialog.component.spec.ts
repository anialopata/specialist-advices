import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowVisitDetailsDialogComponent } from './show-visit-details-dialog.component';

describe('ShowVisitDetailsDialogComponent', () => {
  let component: ShowVisitDetailsDialogComponent;
  let fixture: ComponentFixture<ShowVisitDetailsDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowVisitDetailsDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowVisitDetailsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

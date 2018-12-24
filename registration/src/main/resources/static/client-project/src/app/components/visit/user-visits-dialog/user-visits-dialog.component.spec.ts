import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserVisitsDialogComponent } from './user-visits-dialog.component';

describe('UserVisitsDialogComponent', () => {
  let component: UserVisitsDialogComponent;
  let fixture: ComponentFixture<UserVisitsDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserVisitsDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserVisitsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpecialistsDialogComponent } from './specialists-dialog.component';

describe('SpecialistsDialogComponent', () => {
  let component: SpecialistsDialogComponent;
  let fixture: ComponentFixture<SpecialistsDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpecialistsDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpecialistsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

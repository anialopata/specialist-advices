import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllSpecialistsComponent } from './all-specialists.component';

describe('AllSpecialistsComponent', () => {
  let component: AllSpecialistsComponent;
  let fixture: ComponentFixture<AllSpecialistsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllSpecialistsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllSpecialistsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

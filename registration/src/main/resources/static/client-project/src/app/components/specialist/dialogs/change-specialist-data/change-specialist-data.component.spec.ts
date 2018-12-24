import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeSpecialistDataComponent } from './change-specialist-data.component';

describe('ChangeSpecialistDataComponent', () => {
  let component: ChangeSpecialistDataComponent;
  let fixture: ComponentFixture<ChangeSpecialistDataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeSpecialistDataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeSpecialistDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

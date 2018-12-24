import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteVisitDialogComponent } from './delete-visit-dialog.component';

describe('DeleteVisitDialogComponent', () => {
  let component: DeleteVisitDialogComponent;
  let fixture: ComponentFixture<DeleteVisitDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteVisitDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteVisitDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

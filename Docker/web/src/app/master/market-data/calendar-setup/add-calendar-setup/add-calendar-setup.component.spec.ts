import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCalendarSetupComponent } from './add-calendar-setup.component';

describe('AddCalendarSetupComponent', () => {
  let component: AddCalendarSetupComponent;
  let fixture: ComponentFixture<AddCalendarSetupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCalendarSetupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCalendarSetupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

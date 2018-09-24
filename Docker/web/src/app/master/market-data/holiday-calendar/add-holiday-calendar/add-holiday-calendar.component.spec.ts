import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddHolidayCalendarComponent } from './add-holiday-calendar.component';

describe('AddHolidayCalendarComponent', () => {
  let component: AddHolidayCalendarComponent;
  let fixture: ComponentFixture<AddHolidayCalendarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddHolidayCalendarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddHolidayCalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

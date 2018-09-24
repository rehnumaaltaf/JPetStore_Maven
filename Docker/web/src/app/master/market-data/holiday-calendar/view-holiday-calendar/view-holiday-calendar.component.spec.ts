import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewHolidayCalendarComponent } from './view-holiday-calendar.component';

describe('ViewHolidayCalendarComponent', () => {
  let component: ViewHolidayCalendarComponent;
  let fixture: ComponentFixture<ViewHolidayCalendarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewHolidayCalendarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewHolidayCalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarSetupComponent } from './calendar-setup.component';

describe('CalendarSetupComponent', () => {
  let component: CalendarSetupComponent;
  let fixture: ComponentFixture<CalendarSetupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CalendarSetupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CalendarSetupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCalendarSetupComponent } from './view-calendar-setup.component';

describe('ViewCalendarSetupComponent', () => {
  let component: ViewCalendarSetupComponent;
  let fixture: ComponentFixture<ViewCalendarSetupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewCalendarSetupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewCalendarSetupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

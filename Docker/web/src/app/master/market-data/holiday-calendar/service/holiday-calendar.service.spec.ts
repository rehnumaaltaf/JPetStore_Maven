import { TestBed, inject } from '@angular/core/testing';

import { HolidayCalendarService } from './holiday-calendar.service';

describe('HolidayCalendarService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HolidayCalendarService]
    });
  });

  it('should be created', inject([HolidayCalendarService], (service: HolidayCalendarService) => {
    expect(service).toBeTruthy();
  }));
});

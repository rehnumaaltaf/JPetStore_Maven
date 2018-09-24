import { TestBed, inject } from '@angular/core/testing';

import { CalendarSetupService } from './calendar-setup.service';

describe('CalendarSetupService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CalendarSetupService]
    });
  });

  it('should be created', inject([CalendarSetupService], (service: CalendarSetupService) => {
    expect(service).toBeTruthy();
  }));
});

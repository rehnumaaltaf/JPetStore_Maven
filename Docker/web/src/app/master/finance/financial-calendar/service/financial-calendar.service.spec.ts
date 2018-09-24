import { TestBed, inject } from '@angular/core/testing';

import { FinancialCalendarService } from './financial-calendar.service';

describe('FinancialCalendarService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FinancialCalendarService]
    });
  });

  it('should be created', inject([FinancialCalendarService], (service: FinancialCalendarService) => {
    expect(service).toBeTruthy();
  }));
});

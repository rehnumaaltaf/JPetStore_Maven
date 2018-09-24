import { TestBed, inject } from '@angular/core/testing';

import { UnitLimitService } from './unit-limit.service';

describe('UnitLimitService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UnitLimitService]
    });
  });

  it('should be created', inject([UnitLimitService], (service: UnitLimitService) => {
    expect(service).toBeTruthy();
  }));
});

import { TestBed, inject } from '@angular/core/testing';

import { ForexLimitService } from './forex-limit.service';

describe('ForexLimitService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ForexLimitService]
    });
  });

  it('should be created', inject([ForexLimitService], (service: ForexLimitService) => {
    expect(service).toBeTruthy();
  }));
});

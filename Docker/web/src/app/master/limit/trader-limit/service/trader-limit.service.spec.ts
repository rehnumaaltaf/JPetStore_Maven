import { TestBed, inject } from '@angular/core/testing';

import { TraderLimitService } from './trader-limit.service';

describe('TraderLimitService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TraderLimitService]
    });
  });

  it('should be created', inject([TraderLimitService], (service: TraderLimitService) => {
    expect(service).toBeTruthy();
  }));
});

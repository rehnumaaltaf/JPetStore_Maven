import { TestBed, inject } from '@angular/core/testing';

import { PartyLimitService } from './party-limit.service';

describe('PartyLimitService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PartyLimitService]
    });
  });

  it('should be created', inject([PartyLimitService], (service: PartyLimitService) => {
    expect(service).toBeTruthy();
  }));
});

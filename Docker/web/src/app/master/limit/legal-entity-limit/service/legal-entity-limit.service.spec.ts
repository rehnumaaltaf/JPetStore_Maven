import { TestBed, inject } from '@angular/core/testing';

import { LegalEntityLimitService } from './legal-entity-limit.service';

describe('LegalEntityLimitService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LegalEntityLimitService]
    });
  });

  it('should be created', inject([LegalEntityLimitService], (service: LegalEntityLimitService) => {
    expect(service).toBeTruthy();
  }));
});

import { TestBed, inject } from '@angular/core/testing';

import { WeightTermsService } from './weight-terms.service';

describe('WeightTermsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WeightTermsService]
    });
  });

  it('should be created', inject([WeightTermsService], (service: WeightTermsService) => {
    expect(service).toBeTruthy();
  }));
});

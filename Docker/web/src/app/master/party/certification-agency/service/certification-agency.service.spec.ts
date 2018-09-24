import { TestBed, inject } from '@angular/core/testing';

import { CertificationAgencyService } from './certification-agency.service';

describe('CertificationAgencyService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CertificationAgencyService]
    });
  });

  it('should be created', inject([CertificationAgencyService], (service: CertificationAgencyService) => {
    expect(service).toBeTruthy();
  }));
});

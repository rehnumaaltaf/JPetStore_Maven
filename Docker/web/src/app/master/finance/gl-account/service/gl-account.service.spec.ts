import { TestBed, inject } from '@angular/core/testing';

import { GlAccountService } from './gl-account.service';

describe('GlAccountService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GlAccountService]
    });
  });

  it('should be created', inject([GlAccountService], (service: GlAccountService) => {
    expect(service).toBeTruthy();
  }));
});

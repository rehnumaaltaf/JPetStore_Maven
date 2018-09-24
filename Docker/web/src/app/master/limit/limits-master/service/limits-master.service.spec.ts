import { TestBed, inject } from '@angular/core/testing';

import { LimitsMasterService } from './limits-master.service';

describe('LimitsMasterService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LimitsMasterService]
    });
  });

  it('should be created', inject([LimitsMasterService], (service: LimitsMasterService) => {
    expect(service).toBeTruthy();
  }));
});

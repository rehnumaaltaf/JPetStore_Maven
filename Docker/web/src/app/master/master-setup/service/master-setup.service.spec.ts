import { TestBed, inject } from '@angular/core/testing';

import { MasterSetupService } from './master-setup.service';

describe('MasterSetupService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MasterSetupService]
    });
  });

  it('should be created', inject([MasterSetupService], (service: MasterSetupService) => {
    expect(service).toBeTruthy();
  }));
});

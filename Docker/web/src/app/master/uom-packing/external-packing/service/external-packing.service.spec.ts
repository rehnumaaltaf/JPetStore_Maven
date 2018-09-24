import { TestBed, inject } from '@angular/core/testing';

import { ExternalPackingService } from './external-packing.service';

describe('ExternalPackingService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ExternalPackingService]
    });
  });

  it('should be created', inject([ExternalPackingService], (service: ExternalPackingService) => {
    expect(service).toBeTruthy();
  }));
});

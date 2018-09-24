import { TestBed, inject } from '@angular/core/testing';

import { InternalPackingService } from './internal-packing.service';

describe('InternalPackingService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [InternalPackingService]
    });
  });

  it('should be created', inject([InternalPackingService], (service: InternalPackingService) => {
    expect(service).toBeTruthy();
  }));
});

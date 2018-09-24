import { TestBed, inject } from '@angular/core/testing';

import { FreightCostMatrixService } from './freight-cost-matrix.service';

describe('FreightCostMatrixService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FreightCostMatrixService]
    });
  });

  it('should be created', inject([FreightCostMatrixService], (service: FreightCostMatrixService) => {
    expect(service).toBeTruthy();
  }));
});

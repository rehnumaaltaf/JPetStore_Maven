import { TestBed, inject } from '@angular/core/testing';

import { CnfCostMatrixService } from './cnf-cost-matrix.service';

describe('CnfCostMatrixService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CnfCostMatrixService]
    });
  });

  it('should be created', inject([CnfCostMatrixService], (service: CnfCostMatrixService) => {
    expect(service).toBeTruthy();
  }));
});

import { TestBed, inject } from '@angular/core/testing';

import { CostMatrixService } from './cost-matrix.service';

describe('CostMatrixService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CostMatrixService]
    });
  });

  it('should be created', inject([CostMatrixService], (service: CostMatrixService) => {
    expect(service).toBeTruthy();
  }));
});

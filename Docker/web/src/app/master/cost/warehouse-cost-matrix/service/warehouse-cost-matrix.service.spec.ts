import { TestBed, inject } from '@angular/core/testing';

import { WarehouseCostMatrixService } from './warehouse-cost-matrix.service';

describe('WarehouseCostMatrixService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WarehouseCostMatrixService]
    });
  });

  it('should be created', inject([WarehouseCostMatrixService], (service: WarehouseCostMatrixService) => {
    expect(service).toBeTruthy();
  }));
});

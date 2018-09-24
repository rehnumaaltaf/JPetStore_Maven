import { TestBed, inject } from '@angular/core/testing';

import { ProductGradeService } from './product-grade.service';

describe('ProductGradeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ProductGradeService]
    });
  });

  it('should be created', inject([ProductGradeService], (service: ProductGradeService) => {
    expect(service).toBeTruthy();
  }));
});

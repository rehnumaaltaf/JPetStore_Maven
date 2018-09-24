import { TestBed, inject } from '@angular/core/testing';

import { ProductLimitService } from './product-limit.service';

describe('ProductLimitService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ProductLimitService]
    });
  });

  it('should be created', inject([ProductLimitService], (service: ProductLimitService) => {
    expect(service).toBeTruthy();
  }));
});

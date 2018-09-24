import { TestBed, inject } from '@angular/core/testing';

import { ProductMasterService } from './product-master.service';

describe('ProductMasterService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ProductMasterService]
    });
  });

  it('should be created', inject([ProductMasterService], (service: ProductMasterService) => {
    expect(service).toBeTruthy();
  }));
});

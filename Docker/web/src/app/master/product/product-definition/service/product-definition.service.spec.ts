import { TestBed, inject } from '@angular/core/testing';

import { ProductDefinitionService } from './product-definition.service';

describe('ProductDefinitionService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ProductDefinitionService]
    });
  });

  it('should be created', inject([ProductDefinitionService], (service: ProductDefinitionService) => {
    expect(service).toBeTruthy();
  }));
});

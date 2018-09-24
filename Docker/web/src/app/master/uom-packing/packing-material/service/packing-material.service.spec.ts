import { TestBed, inject } from '@angular/core/testing';

import { PackingMaterialService } from './packing-material.service';

describe('PackingMaterialService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PackingMaterialService]
    });
  });

  it('should be created', inject([PackingMaterialService], (service: PackingMaterialService) => {
    expect(service).toBeTruthy();
  }));
});

import { TestBed, inject } from '@angular/core/testing';

import { CropYearService } from './crop-year.service';

describe('CropYearService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CropYearService]
    });
  });

  it('should be created', inject([CropYearService], (service: CropYearService) => {
    expect(service).toBeTruthy();
  }));
});

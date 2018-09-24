import { TestBed, inject } from '@angular/core/testing';

import { QualityTemplateService } from './quality-template.service';

describe('QualityTemplateService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [QualityTemplateService]
    });
  });

  it('should be created', inject([QualityTemplateService], (service: QualityTemplateService) => {
    expect(service).toBeTruthy();
  }));
});

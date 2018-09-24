import { TestBed, inject } from '@angular/core/testing';

import { GradeDefinitionService } from './grade-definition.service';

describe('GradeDefinitionService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GradeDefinitionService]
    });
  });

  it('should be created', inject([GradeDefinitionService], (service: GradeDefinitionService) => {
    expect(service).toBeTruthy();
  }));
});

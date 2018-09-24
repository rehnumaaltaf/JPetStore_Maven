import { TestBed, inject } from '@angular/core/testing';

import { PartyDefinitionService } from './party-definition.service';

describe('PartyDefinitionService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PartyDefinitionService]
    });
  });

  it('should be created', inject([PartyDefinitionService], (service: PartyDefinitionService) => {
    expect(service).toBeTruthy();
  }));
});

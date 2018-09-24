import { TestBed, inject } from '@angular/core/testing';

import { AbstractLinkService } from './abstract-link.service';

describe('AbstractLinkService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AbstractLinkService]
    });
  });

  it('should be created', inject([AbstractLinkService], (service: AbstractLinkService) => {
    expect(service).toBeTruthy();
  }));
});

import { TestBed, inject } from '@angular/core/testing';

import { BlendService } from './blend.service';

describe('BlendService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BlendService]
    });
  });

  it('should be created', inject([BlendService], (service: BlendService) => {
    expect(service).toBeTruthy();
  }));
});

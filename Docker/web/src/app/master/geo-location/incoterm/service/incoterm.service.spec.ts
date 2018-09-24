import { TestBed, inject } from '@angular/core/testing';

import { IncotermService } from './incoterm.service';

describe('IncotermService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [IncotermService]
    });
  });

  it('should be created', inject([IncotermService], (service: IncotermService) => {
    expect(service).toBeTruthy();
  }));
});

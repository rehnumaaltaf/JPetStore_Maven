import { TestBed, inject } from '@angular/core/testing';

import { OutturnService } from './outturn.service';

describe('OutturnService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OutturnService]
    });
  });

  it('should be created', inject([OutturnService], (service: OutturnService) => {
    expect(service).toBeTruthy();
  }));
});

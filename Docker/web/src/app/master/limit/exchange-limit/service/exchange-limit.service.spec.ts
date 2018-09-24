import { TestBed, inject } from '@angular/core/testing';

import { ExchangeLimitService } from './exchange-limit.service';

describe('ExchangeLimitService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ExchangeLimitService]
    });
  });

  it('should be created', inject([ExchangeLimitService], (service: ExchangeLimitService) => {
    expect(service).toBeTruthy();
  }));
});

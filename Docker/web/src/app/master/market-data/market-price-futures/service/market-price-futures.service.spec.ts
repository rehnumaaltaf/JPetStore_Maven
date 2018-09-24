import { TestBed, inject } from '@angular/core/testing';

import { MarketPriceFuturesService } from './market-price-futures.service';

describe('MarketPriceFuturesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MarketPriceFuturesService]
    });
  });

  it('should be created', inject([MarketPriceFuturesService], (service: MarketPriceFuturesService) => {
    expect(service).toBeTruthy();
  }));
});

import { TestBed, inject } from '@angular/core/testing';

import { MarketPriceOptionsService } from './market-price-options.service';

describe('MarketPriceOptionsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MarketPriceOptionsService]
    });
  });

  it('should be created', inject([MarketPriceOptionsService], (service: MarketPriceOptionsService) => {
    expect(service).toBeTruthy();
  }));
});

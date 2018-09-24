import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketPriceFuturesComponent } from './market-price-futures.component';

describe('MarketPriceFuturesComponent', () => {
  let component: MarketPriceFuturesComponent;
  let fixture: ComponentFixture<MarketPriceFuturesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketPriceFuturesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketPriceFuturesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

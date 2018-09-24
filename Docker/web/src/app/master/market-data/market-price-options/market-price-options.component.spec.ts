import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketPriceOptionsComponent } from './market-price-options.component';

describe('MarketPriceOptionsComponent', () => {
  let component: MarketPriceOptionsComponent;
  let fixture: ComponentFixture<MarketPriceOptionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketPriceOptionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketPriceOptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

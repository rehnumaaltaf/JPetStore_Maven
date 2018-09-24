import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewTaxRateComponent } from './view-tax-rate.component';

describe('ViewTaxRateComponent', () => {
  let component: ViewTaxRateComponent;
  let fixture: ComponentFixture<ViewTaxRateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewTaxRateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewTaxRateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

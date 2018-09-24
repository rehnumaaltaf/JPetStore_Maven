import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTaxRateComponent } from './add-tax-rate.component';

describe('AddTaxRateComponent', () => {
  let component: AddTaxRateComponent;
  let fixture: ComponentFixture<AddTaxRateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddTaxRateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTaxRateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

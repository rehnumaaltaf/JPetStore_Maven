import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WeightTermsComponent } from './weight-terms.component';

describe('PaymentTermsComponent', () => {
  let component: WeightTermsComponent;
  let fixture: ComponentFixture<WeightTermsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WeightTermsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WeightTermsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

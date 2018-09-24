import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPaymentTermsComponent } from './view-payment-terms.component';

describe('ViewPaymentTermsComponent', () => {
  let component: ViewPaymentTermsComponent;
  let fixture: ComponentFixture<ViewPaymentTermsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewPaymentTermsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPaymentTermsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

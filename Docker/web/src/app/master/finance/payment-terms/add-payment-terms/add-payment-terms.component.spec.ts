import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPaymentTermsComponent } from './add-payment-terms.component';

describe('AddPaymentTermsComponent', () => {
  let component: AddPaymentTermsComponent;
  let fixture: ComponentFixture<AddPaymentTermsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddPaymentTermsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPaymentTermsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

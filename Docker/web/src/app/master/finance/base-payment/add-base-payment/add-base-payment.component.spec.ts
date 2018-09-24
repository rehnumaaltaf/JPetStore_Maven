import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBasePaymentComponent } from './add-base-payment.component';

describe('AddBasePaymentComponent', () => {
  let component: AddBasePaymentComponent;
  let fixture: ComponentFixture<AddBasePaymentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddBasePaymentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddBasePaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

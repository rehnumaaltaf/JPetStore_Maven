import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewBasePaymentComponent } from './view-base-payment.component';

describe('ViewBasePaymentComponent', () => {
  let component: ViewBasePaymentComponent;
  let fixture: ComponentFixture<ViewBasePaymentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewBasePaymentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewBasePaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

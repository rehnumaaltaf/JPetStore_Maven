import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipmentMonthComponent } from './shipment-month.component';

describe('ShipmentMonthComponent', () => {
  let component: ShipmentMonthComponent;
  let fixture: ComponentFixture<ShipmentMonthComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShipmentMonthComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipmentMonthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

import { TestBed, inject } from '@angular/core/testing';

import { ShipmentMonthService } from './shipment-month.service';

describe('ShipmentMonthService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ShipmentMonthService]
    });
  });

  it('should be created', inject([ShipmentMonthService], (service: ShipmentMonthService) => {
    expect(service).toBeTruthy();
  }));
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FreightCostMatrixComponent } from './freight-cost-matrix.component';

describe('FreightCostMatrixComponent', () => {
  let component: FreightCostMatrixComponent;
  let fixture: ComponentFixture<FreightCostMatrixComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FreightCostMatrixComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FreightCostMatrixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

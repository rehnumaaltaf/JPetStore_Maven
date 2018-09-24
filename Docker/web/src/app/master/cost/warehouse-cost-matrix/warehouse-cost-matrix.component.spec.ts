import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WarehouseCostMatrixComponent } from './warehouse-cost-matrix.component';

describe('WarehouseCostMatrixComponent', () => {
  let component: WarehouseCostMatrixComponent;
  let fixture: ComponentFixture<WarehouseCostMatrixComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WarehouseCostMatrixComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WarehouseCostMatrixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

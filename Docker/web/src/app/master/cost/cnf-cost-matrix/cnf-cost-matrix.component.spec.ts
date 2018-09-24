import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CnfCostMatrixComponent } from './cnf-cost-matrix.component';

describe('CnfCostMatrixComponent', () => {
  let component: CnfCostMatrixComponent;
  let fixture: ComponentFixture<CnfCostMatrixComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CnfCostMatrixComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CnfCostMatrixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

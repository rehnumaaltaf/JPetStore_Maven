import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCnfCostMatrixComponent } from './view-cnf-cost-matrix.component';

describe('ViewCnfCostMatrixComponent', () => {
  let component: ViewCnfCostMatrixComponent;
  let fixture: ComponentFixture<ViewCnfCostMatrixComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewCnfCostMatrixComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewCnfCostMatrixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

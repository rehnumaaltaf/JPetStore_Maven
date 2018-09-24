import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCnfCostMatrixComponent } from './add-cnf-cost-matrix.component';

describe('AddCnfCostMatrixComponent', () => {
  let component: AddCnfCostMatrixComponent;
  let fixture: ComponentFixture<AddCnfCostMatrixComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCnfCostMatrixComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCnfCostMatrixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

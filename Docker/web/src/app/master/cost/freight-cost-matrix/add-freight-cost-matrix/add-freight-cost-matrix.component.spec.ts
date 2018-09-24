import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFreightCostMatrixComponent } from './add-freight-cost-matrix.component';

describe('AddFreightCostMatrixComponent', () => {
  let component: AddFreightCostMatrixComponent;
  let fixture: ComponentFixture<AddFreightCostMatrixComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddFreightCostMatrixComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFreightCostMatrixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

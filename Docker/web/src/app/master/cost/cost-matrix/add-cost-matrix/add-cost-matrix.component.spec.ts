import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCostMatrixComponent } from './add-cost-matrix.component';

describe('AddCostMatrixComponent', () => {
  let component: AddCostMatrixComponent;
  let fixture: ComponentFixture<AddCostMatrixComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCostMatrixComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCostMatrixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

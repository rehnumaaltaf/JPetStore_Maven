import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CostMatrixComponent } from './cost-matrix.component';

describe('CostMatrixComponent', () => {
  let component: CostMatrixComponent;
  let fixture: ComponentFixture<CostMatrixComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CostMatrixComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CostMatrixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

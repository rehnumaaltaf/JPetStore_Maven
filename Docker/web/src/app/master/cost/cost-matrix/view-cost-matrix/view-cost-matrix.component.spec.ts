import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCostMatrixComponent } from './view-cost-matrix.component';

describe('ViewCostMatrixComponent', () => {
  let component: ViewCostMatrixComponent;
  let fixture: ComponentFixture<ViewCostMatrixComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewCostMatrixComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewCostMatrixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

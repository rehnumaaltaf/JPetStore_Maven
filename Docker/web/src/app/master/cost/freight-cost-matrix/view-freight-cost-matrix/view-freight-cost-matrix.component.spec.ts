import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFreightCostMatrixComponent } from './view-freight-cost-matrix.component';

describe('ViewFreightCostMatrixComponent', () => {
  let component: ViewFreightCostMatrixComponent;
  let fixture: ComponentFixture<ViewFreightCostMatrixComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewFreightCostMatrixComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewFreightCostMatrixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

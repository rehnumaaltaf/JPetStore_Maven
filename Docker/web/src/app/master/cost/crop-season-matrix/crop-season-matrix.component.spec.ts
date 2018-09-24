import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CropSeasonMatrixComponent } from './crop-season-matrix.component';

describe('CropSeasonMatrixComponent', () => {
  let component: CropSeasonMatrixComponent;
  let fixture: ComponentFixture<CropSeasonMatrixComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CropSeasonMatrixComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CropSeasonMatrixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

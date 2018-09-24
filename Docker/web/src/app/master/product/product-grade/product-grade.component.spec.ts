import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductGradeComponent } from './product-grade.component';

describe('ProductGradeComponent', () => {
  let component: ProductGradeComponent;
  let fixture: ComponentFixture<ProductGradeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductGradeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductGradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

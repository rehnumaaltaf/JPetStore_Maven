import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProductGradeComponent } from './add-product-grade.component';

describe('AddProductGradeComponent', () => {
  let component: AddProductGradeComponent;
  let fixture: ComponentFixture<AddProductGradeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddProductGradeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddProductGradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

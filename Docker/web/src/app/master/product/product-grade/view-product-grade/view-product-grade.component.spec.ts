import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewProductGradeComponent } from './view-product-grade.component';

describe('ViewProductGradeComponent', () => {
  let component: ViewProductGradeComponent;
  let fixture: ComponentFixture<ViewProductGradeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewProductGradeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewProductGradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProductLimitComponent } from './add-product-limit.component';

describe('AddProductLimitComponent', () => {
  let component: AddProductLimitComponent;
  let fixture: ComponentFixture<AddProductLimitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddProductLimitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddProductLimitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

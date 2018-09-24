import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductLimitComponent } from './product-limit.component';

describe('ProductLimitComponent', () => {
  let component: ProductLimitComponent;
  let fixture: ComponentFixture<ProductLimitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductLimitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductLimitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

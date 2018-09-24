import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewProductLimitComponent } from './view-product-limit.component';

describe('ViewProductLimitComponent', () => {
  let component: ViewProductLimitComponent;
  let fixture: ComponentFixture<ViewProductLimitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewProductLimitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewProductLimitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

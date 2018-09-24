import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewProductMasterComponent } from './view-product-master.component';

describe('ViewProductMasterComponent', () => {
  let component: ViewProductMasterComponent;
  let fixture: ComponentFixture<ViewProductMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewProductMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewProductMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

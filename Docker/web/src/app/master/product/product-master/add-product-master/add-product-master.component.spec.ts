import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProductMasterComponent } from './add-product-master.component';

describe('AddProductMasterComponent', () => {
  let component: AddProductMasterComponent;
  let fixture: ComponentFixture<AddProductMasterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddProductMasterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddProductMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

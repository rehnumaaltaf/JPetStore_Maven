import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPackingMaterialComponent } from './add-packing-material.component';

describe('AddPackingMaterialComponent', () => {
  let component: AddPackingMaterialComponent;
  let fixture: ComponentFixture<AddPackingMaterialComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddPackingMaterialComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPackingMaterialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

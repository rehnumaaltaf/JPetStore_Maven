import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPackingMaterialComponent } from './view-packing-material.component';

describe('ViewPackingMaterialComponent', () => {
  let component: ViewPackingMaterialComponent;
  let fixture: ComponentFixture<ViewPackingMaterialComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewPackingMaterialComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPackingMaterialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

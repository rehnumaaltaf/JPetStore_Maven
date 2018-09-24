import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewInternalPackingComponent } from './view-internal-packing.component';

describe('ViewInternalPackingComponent', () => {
  let component: ViewInternalPackingComponent;
  let fixture: ComponentFixture<ViewInternalPackingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewInternalPackingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewInternalPackingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

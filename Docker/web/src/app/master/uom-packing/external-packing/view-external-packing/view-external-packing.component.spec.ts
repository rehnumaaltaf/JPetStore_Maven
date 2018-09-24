import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewExternalPackingComponent } from './view-external-packing.component';

describe('ViewExternalPackingComponent', () => {
  let component: ViewExternalPackingComponent;
  let fixture: ComponentFixture<ViewExternalPackingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewExternalPackingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewExternalPackingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

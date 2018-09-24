import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewGeoComponent } from './view-geo.component';

describe('ViewIncoComponent', () => {
  let component: ViewGeoComponent;
  let fixture: ComponentFixture<ViewGeoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ViewGeoComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewGeoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

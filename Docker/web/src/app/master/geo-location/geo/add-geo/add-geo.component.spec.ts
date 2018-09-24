import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddGeoComponent } from './add-geo.component';

describe('AddGeoComponent', () => {
  let component: AddGeoComponent;
  let fixture: ComponentFixture<AddGeoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddGeoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddGeoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

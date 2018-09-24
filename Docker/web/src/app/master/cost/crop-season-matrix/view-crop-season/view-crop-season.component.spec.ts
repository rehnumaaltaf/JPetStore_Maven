import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCropSeasonComponent } from './view-crop-season.component';

describe('ViewCropSeasonComponent', () => {
  let component: ViewCropSeasonComponent;
  let fixture: ComponentFixture<ViewCropSeasonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewCropSeasonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewCropSeasonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

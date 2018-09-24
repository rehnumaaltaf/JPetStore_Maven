import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCropSeasonComponent } from './add-crop-season.component';

describe('AddCropSeasonComponent', () => {
  let component: AddCropSeasonComponent;
  let fixture: ComponentFixture<AddCropSeasonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCropSeasonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCropSeasonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

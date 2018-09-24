import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CropYearComponent } from './crop-year.component';

describe('CropYearComponent', () => {
  let component: CropYearComponent;
  let fixture: ComponentFixture<CropYearComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CropYearComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CropYearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

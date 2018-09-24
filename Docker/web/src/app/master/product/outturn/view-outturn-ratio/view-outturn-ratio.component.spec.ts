import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewOutturnRatioComponent } from './view-outturn-ratio.component';

describe('ViewOutturnRatioComponent', () => {
  let component: ViewOutturnRatioComponent;
  let fixture: ComponentFixture<ViewOutturnRatioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewOutturnRatioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewOutturnRatioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

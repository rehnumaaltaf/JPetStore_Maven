import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OutturnRatioComponent } from './outturn-ratio.component';

describe('OutturnRatioComponent', () => {
  let component: OutturnRatioComponent;
  let fixture: ComponentFixture<OutturnRatioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OutturnRatioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OutturnRatioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

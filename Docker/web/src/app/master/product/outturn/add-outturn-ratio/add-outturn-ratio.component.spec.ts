import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddOutturnRatioComponent } from './add-outturn-ratio.component';

describe('AddOutturnRatioComponent', () => {
  let component: AddOutturnRatioComponent;
  let fixture: ComponentFixture<AddOutturnRatioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddOutturnRatioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddOutturnRatioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewWeightTermsComponent } from './view-weight-terms.component';

describe('ViewWeightTermsComponent', () => {
  let component: ViewWeightTermsComponent;
  let fixture: ComponentFixture<ViewWeightTermsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewWeightTermsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewWeightTermsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

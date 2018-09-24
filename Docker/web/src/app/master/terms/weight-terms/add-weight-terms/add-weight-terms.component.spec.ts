import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddWeightTermsComponent } from './add-weight-terms.component';

describe('AddWeightTermsComponent', () => {
  let component: AddWeightTermsComponent;
  let fixture: ComponentFixture<AddWeightTermsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddWeightTermsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddWeightTermsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

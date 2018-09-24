import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFinancialCalComponent } from './view-financial-cal.component';

describe('ViewFinancialCalComponent', () => {
  let component: ViewFinancialCalComponent;
  let fixture: ComponentFixture<ViewFinancialCalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewFinancialCalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewFinancialCalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

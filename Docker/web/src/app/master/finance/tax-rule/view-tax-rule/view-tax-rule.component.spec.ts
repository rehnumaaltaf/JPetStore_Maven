import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewTaxRuleComponent } from './view-tax-rule.component';

describe('ViewTaxRuleComponent', () => {
  let component: ViewTaxRuleComponent;
  let fixture: ComponentFixture<ViewTaxRuleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewTaxRuleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewTaxRuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTaxRuleComponent } from './add-tax-rule.component';

describe('AddTaxRuleComponent', () => {
  let component: AddTaxRuleComponent;
  let fixture: ComponentFixture<AddTaxRuleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddTaxRuleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTaxRuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

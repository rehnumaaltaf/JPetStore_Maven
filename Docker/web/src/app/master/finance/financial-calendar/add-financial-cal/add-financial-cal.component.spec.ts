import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFinancialCalComponent } from './add-financial-cal.component';

describe('AddFinancialCalComponent', () => {
  let component: AddFinancialCalComponent;
  let fixture: ComponentFixture<AddFinancialCalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddFinancialCalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFinancialCalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

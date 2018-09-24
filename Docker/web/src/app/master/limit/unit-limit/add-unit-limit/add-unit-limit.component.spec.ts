import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUnitLimitComponent } from './add-unit-limit.component';

describe('AddUnitLimitComponent', () => {
  let component: AddUnitLimitComponent;
  let fixture: ComponentFixture<AddUnitLimitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddUnitLimitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddUnitLimitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

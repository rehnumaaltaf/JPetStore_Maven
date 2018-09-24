import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnitLimitComponent } from './unit-limit.component';

describe('UnitLimitComponent', () => {
  let component: UnitLimitComponent;
  let fixture: ComponentFixture<UnitLimitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnitLimitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnitLimitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

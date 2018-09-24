import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewUnitLimitComponent } from './view-unit-limit.component';

describe('ViewUnitLimitComponent', () => {
  let component: ViewUnitLimitComponent;
  let fixture: ComponentFixture<ViewUnitLimitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewUnitLimitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewUnitLimitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

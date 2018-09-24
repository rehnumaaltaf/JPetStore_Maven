import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewTraderLimitComponent } from './view-trader-limit.component';

describe('ViewTraderLimitComponent', () => {
  let component: ViewTraderLimitComponent;
  let fixture: ComponentFixture<ViewTraderLimitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewTraderLimitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewTraderLimitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
